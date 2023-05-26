package project.healthcommunity.trainer.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import project.healthcommunity.certificate.dto.CertificateResponse;
import project.healthcommunity.certificate.dto.QCertificateForm;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.TrainerResponse;
import project.healthcommunity.trainer.dto.TrainerSearchCond;
import project.healthcommunity.trainer.exception.TrainerNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static org.springframework.util.StringUtils.hasText;
import static project.healthcommunity.certificate.domain.QCertificate.certificate;
import static project.healthcommunity.trainer.domain.QTrainer.*;

@RequiredArgsConstructor
@Repository
public class TrainerRepositoryImpl implements TrainerRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final TrainerJpaRepository trainerJpaRepository;

    @Override
    public Page<TrainerResponse> search(TrainerSearchCond condition, Pageable pageable) {
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

        List<TrainerResponse> trainerResponses = trainers.stream().map(t-> TrainerResponse.createByTrainer(t)).collect(toList());

        List<Long> trainerIds = trainerResponses.stream().map(TrainerResponse::getId).collect(toList());

        Map<Long, List<CertificateResponse>> certificateDtoMap = findCertificateDtoMap(trainerIds);

        trainerResponses.forEach(t-> {
            t.setCertificateResponseList(certificateDtoMap.get(t.getId()));
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



        return PageableExecutionUtils.getPage(trainerResponses, pageable, countQuery::fetchOne);
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

    @Override
    public Trainer save(Trainer trainer) {
        return trainerJpaRepository.save(trainer);
    }

    @Override
    public Trainer getByLoginId(String loginId) {
        return trainerJpaRepository.findByLoginId(loginId).orElseThrow(TrainerNotFoundException::new);
    }

    @Override
    public void deleteById(Long trainerId) {
        trainerJpaRepository.deleteById(trainerId);
    }

    @Override
    public void deleteAll() {
        trainerJpaRepository.deleteAll();
    }

    @Override
    public Trainer getById(Long trainerId) {
        return trainerJpaRepository.findById(trainerId).orElseThrow(TrainerNotFoundException::new);
    }

    @Override
    public List<Trainer> findAll() {
        return trainerJpaRepository.findAll();
    }

    @Override
    public List<Trainer> getByTrainerName(String trainerName) {
        return trainerJpaRepository.findByTrainerName(trainerName).orElseThrow(TrainerNotFoundException::new);
    }

    @Override
    public Optional<List<Trainer>> findByTrainerName(String trainerName) {
        return trainerJpaRepository.findByTrainerName(trainerName);
    }

    @Override
    public Optional<Trainer> findByLoginId(String loginId) {
        return trainerJpaRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<Trainer> findById(Long trainerId) {
        return trainerJpaRepository.findById(trainerId);
    }


    private Map<Long, List<CertificateResponse>> findCertificateDtoMap(List<Long> trainerIds) {
        List<CertificateResponse> certificateResponseList = queryFactory
                .select(new QCertificateForm(certificate))
                .from(certificate)
                .leftJoin(certificate.trainer, trainer).fetchJoin()
                .where(trainer.id.in(trainerIds))
                .fetch();

        Map<Long, List<CertificateResponse>> certificateDtoMap =
                certificateResponseList.stream().collect(groupingBy(CertificateResponse::getTrainerId));
        return certificateDtoMap;
    }

}
