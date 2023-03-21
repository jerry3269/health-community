package project.healthcommunity.trainer.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import project.healthcommunity.certificate.dto.CertificateDto;
import project.healthcommunity.certificate.dto.QCertificateDto;
import project.healthcommunity.trainer.domain.QTrainer;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.TrainerResult;
import project.healthcommunity.trainer.dto.TrainerSearchCond;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.springframework.util.StringUtils.hasText;
import static project.healthcommunity.certificate.domain.QCertificate.certificate;
import static project.healthcommunity.trainer.domain.QTrainer.*;

@RequiredArgsConstructor
public class TrainerRepositoryImpl implements TrainerRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TrainerResult> search(TrainerSearchCond condition, Pageable pageable) {
        List<Trainer> trainers = queryFactory
                .selectFrom(trainer)
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

        List<TrainerResult> trainerResults = trainers.stream().map(TrainerResult::new).collect(toList());

        List<Long> trainerIds = trainerResults.stream().map(TrainerResult::getId).collect(toList());

        Map<Long, List<CertificateDto>> certificateDtoMap = findCertificateDtoMap(trainerIds);

        trainerResults.forEach(t-> {
            t.setCertificateDtoList(certificateDtoMap.get(t.getId()));
        });

        JPAQuery<Long> countQuery = queryFactory
                .select(trainer.count())
                .from(trainer)
                .where(
                        trainerNameEq(condition.getTrainerName()),
                        ageGoe(condition.getAgeGoe()),
                        careerGoe(condition.getCareerGoe()),
                        certificateCountGoe(condition.getCertificateCountGoe()),
                        likesGoe(condition.getLikesGoe()),
                        postCountGoe(condition.getPostCountGoe()),
                        commentCountGoe(condition.getCommentCountGoe()));



        return PageableExecutionUtils.getPage(trainerResults, pageable, countQuery::fetchOne);
    }



    private Map<Long, List<CertificateDto>> findCertificateDtoMap(List<Long> trainerIds) {
        List<CertificateDto> certificateDtoList = queryFactory
                .select(new QCertificateDto(certificate))
                .from(certificate)
                .leftJoin(certificate.trainer, trainer).fetchJoin()
                .where(trainer.id.in(trainerIds))
                .fetch();

        Map<Long, List<CertificateDto>> certificateDtoMap =
                certificateDtoList.stream().collect(groupingBy(CertificateDto::getTrainerId));
        return certificateDtoMap;
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
        return certificateCountGoe != null ? trainer.certificateCount.goe(certificateCountGoe) : null;
    }

    private BooleanExpression likesGoe(Integer likesGoe) {
        return likesGoe != null ? trainer.likes.goe(likesGoe) : null;
    }

    private BooleanExpression postCountGoe(Integer postCountGoe) {
        return postCountGoe != null ? trainer.postCount.goe(postCountGoe) : null;
    }

    private BooleanExpression commentCountGoe(Integer commentCountGoe) {
        return commentCountGoe != null ? trainer.commentCount.goe(commentCountGoe) : null;
    }
}
