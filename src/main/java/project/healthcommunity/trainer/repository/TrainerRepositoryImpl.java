package project.healthcommunity.trainer.repository;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import project.healthcommunity.certificate.domain.QCertificate;
import project.healthcommunity.trainer.domain.QTrainer;
import project.healthcommunity.trainer.dto.QTrainerDto;
import project.healthcommunity.trainer.dto.TrainerResult;
import project.healthcommunity.trainer.dto.TrainerSearchCond;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.springframework.util.StringUtils.*;
import static project.healthcommunity.certificate.domain.QCertificate.*;
import static project.healthcommunity.trainer.domain.QTrainer.*;

@RequiredArgsConstructor
public class TrainerRepositoryImpl implements TrainerRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<TrainerResult> search(TrainerSearchCond condition) {
        List<Tuple> fetch = queryFactory
                .select(new QTrainerDto(trainer),
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
                        f.get(new QTrainerDto(trainer)),
                        f.get(trainer.likes),
                        f.get(trainer.certificates.size()),
                        f.get(trainer.postList.size()),
                        f.get(trainer.commentList.size())))
                .collect(toList());

        return collect;
    }

    @Override
    public Page<TrainerResult> searchPage(TrainerSearchCond condition, Pageable pageable) {

        List<Tuple> fetch = queryFactory
                .select(new QTrainerDto(trainer),
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
                        f.get(new QTrainerDto(trainer)),
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
