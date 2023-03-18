package project.healthcommunity.comment.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.healthcommunity.comment.dto.QCommentDto is a Querydsl Projection type for CommentDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCommentDto extends ConstructorExpression<CommentDto> {

    private static final long serialVersionUID = -1718480780L;

    public QCommentDto(com.querydsl.core.types.Expression<? extends project.healthcommunity.comment.domain.Comment> comment) {
        super(CommentDto.class, new Class<?>[]{project.healthcommunity.comment.domain.Comment.class}, comment);
    }

}

