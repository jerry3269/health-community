package project.healthcommunity.post.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.healthcommunity.post.dto.QPostResult is a Querydsl Projection type for PostResult
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostResult extends ConstructorExpression<PostResult> {

    private static final long serialVersionUID = 1841760452L;

    public QPostResult(com.querydsl.core.types.Expression<? extends project.healthcommunity.post.domain.Post> post) {
        super(PostResult.class, new Class<?>[]{project.healthcommunity.post.domain.Post.class}, post);
    }

}

