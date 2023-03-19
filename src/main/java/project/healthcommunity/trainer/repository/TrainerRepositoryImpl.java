package project.healthcommunity.trainer.repository;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import project.healthcommunity.certificate.domain.QCertificate;
import project.healthcommunity.certificate.dto.CertificateDto;
import project.healthcommunity.certificate.dto.QCertificateDto;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.QTrainerDto;
import project.healthcommunity.trainer.dto.TrainerResult;
import project.healthcommunity.trainer.dto.TrainerSearchCond;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static org.springframework.util.StringUtils.*;
import static project.healthcommunity.certificate.domain.QCertificate.*;
import static project.healthcommunity.trainer.domain.QTrainer.*;

@RequiredArgsConstructor
public class TrainerRepositoryImpl implements TrainerRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<TrainerResult> search(TrainerSearchCond condition) {
        List<TrainerResult> trainerResults = getTrainerResults(condition);

        List<Long> trainerIds = toTrainerIds(trainerResults);

        Map<Long, List<CertificateDto>> certificateDtoMap = findCertificateDtoMap(trainerIds);

        trainerResults.forEach(t -> t.getTrainerDto().setCertificateDtoList(certificateDtoMap.get(t.getTrainerDto().getId())));

        return trainerResults;
    }

    private List<TrainerResult> getTrainerResults(TrainerSearchCond condition) {
        List<Tuple> fetch
                = queryFactory
                .select(
                        new QTrainerDto(trainer.id, trainer.trainerName, trainer.age, trainer.career),
                        trainer.likes,
                        trainer.certificates.size(),
                        trainer.postList.size(),
                        trainer.commentList.size())
                .from(trainer)
                .where(
                        trainerNameEq(condition.getTrainerName()),
                        ageGoe(condition.getAgeGoe()),
                        careerGoe(condition.getCareerGoe()),
                        certificateCountGoe(condition.getCertificateCountGoe()),
                        likesGoe(condition.getLikesGoe()),
                        postCountGoe(condition.getPostCountGoe()),
                        commentCountGoe(condition.getCommentCountGoe()))
                .fetch();

        List<TrainerResult> collect = fetch.stream()
                .map(f -> new TrainerResult(
                        f.get(new QTrainerDto(trainer.id, trainer.trainerName, trainer.age, trainer.career)),
                        f.get(trainer.likes),
                        f.get(trainer.certificates.size()),
                        f.get(trainer.postList.size()),
                        f.get(trainer.commentList.size())))
                .collect(toList());

        return collect;
    }


    private List<Long> toTrainerIds(List<TrainerResult> trainerResults) {
        return trainerResults.stream().map(t->
                t.getTrainerDto().getId()).collect(toList());
    }

    @Override
    public Page<TrainerResult> searchPage_optimization(TrainerSearchCond condition, Pageable pageable) {
        Page<TrainerResult> trainerResults = searchPage(condition, pageable);
        List<Long> trainerIds = toTrainerIds(trainerResults);

        Map<Long, List<CertificateDto>> certificateDtoMap = findCertificateDtoMap(trainerIds);


        trainerResults.forEach(t -> t.getTrainerDto().setCertificateDtoList(certificateDtoMap.get(t.getTrainerDto().getId())));
        return trainerResults;
    }

    private List<Long> toTrainerIds(Page<TrainerResult> trainerResults) {
        return trainerResults.stream().map(t ->
                t.getTrainerDto().getId()).collect(toList());
    }

    private Map<Long, List<CertificateDto>> findCertificateDtoMap(List<Long> trainerIds) {
        List<CertificateDto> fetch = queryFactory
                .select(new QCertificateDto(certificate))
                .from(certificate)
                .leftJoin(certificate.trainer, trainer)
                .fetch();

        Map<Long, List<CertificateDto>> certificateDtoMap =
                fetch.stream().collect(groupingBy(CertificateDto::getTrainerId));
        return certificateDtoMap;
    }

    @Override
    public Page<TrainerResult> searchPage(TrainerSearchCond condition, Pageable pageable) {

        List<Tuple> fetch = queryFactory
                .select(new QTrainerDto(trainer.id, trainer.trainerName, trainer.age, trainer.career),
                        trainer.likes,
                        trainer.certificates.size(),
                        trainer.postList.size(),
                        trainer.commentList.size())
                .from(trainer)
                .where(
                        trainerNameEq(condition.getTrainerName()),
                        ageGoe(condition.getAgeGoe()),
                        careerGoe(condition.getCareerGoe()),
                        certificateCountGoe(condition.getCertificateCountGoe()),
                        likesGoe(condition.getLikesGoe()),
                        postCountGoe(condition.getPostCountGoe()),
                        commentCountGoe(condition.getCommentCountGoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<TrainerResult> collect = fetch.stream()
                .map(f -> new TrainerResult(
                        f.get(new QTrainerDto(trainer.id, trainer.trainerName, trainer.age, trainer.career)),
                        f.get(trainer.likes),
                        f.get(trainer.certificates.size()),
                        f.get(trainer.postList.size()),
                        f.get(trainer.commentList.size())))
                .collect(toList());

        JPAQuery<Long> countQuery = queryFactory
                .select(trainer.count())
                .from(trainer)
                .where(trainerNameEq(condition.getTrainerName()),
                        ageGoe(condition.getAgeGoe()),
                        careerGoe(condition.getCareerGoe()),
                        certificateCountGoe(condition.getCertificateCountGoe()),
                        likesGoe(condition.getLikesGoe()),
                        postCountGoe(condition.getPostCountGoe()),
                        commentCountGoe(condition.getCommentCountGoe()));

        return PageableExecutionUtils.getPage(collect, pageable, countQuery::fetchOne);
    }

    private BooleanExpression trainerNameEq(String trainerName) {
        return hasText(trainerName) ? trainer.trainerName.eq(trainerName) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? trainer.age.goe(ageGoe) : null;
    }

    private BooleanExpression careerGoe(Integer careerGoe) {
        return careerGoe != null ? trainer.career.goe(careerGoe) : null;
    }

    private BooleanExpression certificateCountGoe(Integer certificateCountGoe) {
        return certificateCountGoe != null ? trainer.certificates.size().goe(certificateCountGoe) : null;
    }

    private BooleanExpression likesGoe(Integer likesGoe) {
        return likesGoe != null ? trainer.likes.goe(likesGoe) : null;
    }

    private BooleanExpression postCountGoe(Integer postCountGoe) {
        return postCountGoe != null ? trainer.postList.size().goe(postCountGoe) : null;
    }

    private BooleanExpression commentCountGoe(Integer commentCountGoe) {
        return commentCountGoe != null ? trainer.commentList.size().goe(commentCountGoe) : null;
    }
}
