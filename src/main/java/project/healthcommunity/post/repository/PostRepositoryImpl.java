package project.healthcommunity.post.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import project.healthcommunity.comment.dto.CommentDto;
import project.healthcommunity.comment.dto.QCommentDto;
import project.healthcommunity.post.dto.PostResult;
import project.healthcommunity.post.dto.PostSearchCond;
import project.healthcommunity.post.dto.QPostResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.springframework.util.StringUtils.*;
import static project.healthcommunity.categorypost.domain.QCategoryPost.*;
import static project.healthcommunity.comment.domain.QComment.*;
import static project.healthcommunity.post.domain.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public Page<PostResult> search(PostSearchCond condition, Pageable pageable) {

        List<PostResult> postResults = queryFactory
                .select(new QPostResult(post))
                .from(post)
                .where(
                        titleContain(condition.getTitle()),
                        contentContain(condition.getContent()),
                        likesGoe(condition.getLikesGoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Long> postIds = findPostIds(postResults);

        Map<Long, List<CommentDto>> commentDtoMap = findCommentCountMap(postIds);

        for (PostResult p : postResults) {
            p.setCommentCount(
                    commentDtoMap.containsKey(p.getId()) ? commentDtoMap.get(p.getId()).size() : 0);
        }

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(
                        titleContain(condition.getTitle()),
                        contentContain(condition.getContent()),
                        likesGoe(condition.getLikesGoe()));

        return PageableExecutionUtils.getPage(postResults, pageable, countQuery::fetchOne);
    }

    private List<Long> findPostIds(List<PostResult> postResults) {
        return postResults.stream().map(p -> p.getId()).collect(toList());
    }

    private Map<Long, List<CommentDto>> findCommentCountMap(List<Long> postIds) {
        List<CommentDto> fetch = queryFactory
                .select(new QCommentDto(comment))
                .from(comment)
                .leftJoin(comment.post).fetchJoin()
                .where(comment.post.id.in(postIds))
                .fetch();

        Map<Long, List<CommentDto>> commentDtoMap = fetch.stream().collect(groupingBy(CommentDto::getPostId));
        return commentDtoMap;
    }

    private BooleanExpression titleContain(String title) {
        return hasText(title) ? post.title.contains(title) : null;
    }

    private BooleanExpression contentContain(String content) {
        return hasText(content) ? post.content.contains(content) : null;
    }

    private BooleanExpression likesGoe(Integer likesGoe) {
        return likesGoe != null ? post.likes.goe(likesGoe) : null;
    }

}