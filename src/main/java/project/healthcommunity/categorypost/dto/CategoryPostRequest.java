package project.healthcommunity.categorypost.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.categorypost.domain.CategoryPost;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryPostRequest {
    @NotNull
    private Long categoryPostId;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long postId;

    @QueryProjection
    public CategoryPostRequest(CategoryPost categoryPost) {
        this.categoryPostId = categoryPost.getId();
        this.categoryId = categoryPost.getCategory().getId();
        this.postId = categoryPost.getPost().getId();
    }

}
