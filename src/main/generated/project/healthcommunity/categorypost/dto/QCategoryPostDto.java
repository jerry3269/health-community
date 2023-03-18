package project.healthcommunity.categorypost.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.healthcommunity.categorypost.dto.QCategoryPostDto is a Querydsl Projection type for CategoryPostDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCategoryPostDto extends ConstructorExpression<CategoryPostDto> {

    private static final long serialVersionUID = 746816440L;

    public QCategoryPostDto(com.querydsl.core.types.Expression<? extends project.healthcommunity.categorypost.domain.CategoryPost> categoryPost) {
        super(CategoryPostDto.class, new Class<?>[]{project.healthcommunity.categorypost.domain.CategoryPost.class}, categoryPost);
    }

}

