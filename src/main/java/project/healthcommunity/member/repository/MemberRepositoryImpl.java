package project.healthcommunity.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.MemberResponse;
import project.healthcommunity.member.dto.MemberSearchCond;
import project.healthcommunity.member.dto.QMemberResponse;
import project.healthcommunity.member.exception.MemberNotFoundException;


import java.util.List;

import static org.springframework.util.StringUtils.*;
import static project.healthcommunity.member.domain.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Page<MemberResponse> search(MemberSearchCond condition, Pageable pageable) {

        List<MemberResponse> content = queryFactory
                .select(new QMemberResponse(member))
                .from(member)
                .where(
                        usernameEq(condition.getUsername()),
                        ageGoe(condition.getAgeGoe()),
                        commentCountGoe(condition.getCommentCountGoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member)
                .where(
                        usernameEq(condition.getUsername()),
                        ageGoe(condition.getAgeGoe()),
                        commentCountGoe(condition.getCommentCountGoe()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }


    private BooleanExpression commentCountGoe(Integer commentCountGoe) {
        return commentCountGoe != null ? member.commentList.size().goe(commentCountGoe) : null;
    }

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public List<Member> findByLoginId(String loginId) {
        return memberJpaRepository.findByLoginId(loginId);
    }


    @Override
    public Member findById(Long id) {
        return memberJpaRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }

    @Override
    public List<Member> findByUsername(String username) {
        return memberJpaRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        memberJpaRepository.deleteById(id);
    }

    @Override
    public Member getByLoginId(String loginId) {
        return memberJpaRepository.findOneByLoginId(loginId).orElseThrow(MemberNotFoundException::new);
    }


}
