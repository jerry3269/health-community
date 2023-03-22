package project.healthcommunity.categorypost.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.categorypost.domain.CategoryPost;

@Data
@AllArgsConstructor
public class CategoryPostDto {

    private Long categoryPostId;
    private Long categoryId;
    private Long postId;

    @QueryProjection
    public CategoryPostDto(CategoryPost categoryPost) {
        this.categoryPostId = categoryPost.getId();
        this.categoryId = categoryPost.getCategory().getId();
        this.postId = categoryPost.getPost().getId();
    }

}
