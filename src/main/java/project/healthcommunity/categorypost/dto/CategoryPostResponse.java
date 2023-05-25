package project.healthcommunity.categorypost.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.healthcommunity.categorypost.domain.CategoryPost;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryPostResponse {
    @NotNull
    private Long categoryId;
    @NotNull
    private Long postId;

    @Builder
    protected CategoryPostResponse(Long categoryId, Long postId) {
        this.categoryId = categoryId;
        this.postId = postId;
    }

    public static CategoryPostResponse createByCategoryPost(CategoryPost categoryPost) {
        return CategoryPostResponse.builder()
                .categoryId(categoryPost.getCategory().getId())
                .postId(categoryPost.getPost().getId())
                .build();
    }
}
