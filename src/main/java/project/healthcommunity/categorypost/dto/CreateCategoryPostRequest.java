package project.healthcommunity.categorypost.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.trainer.dto.CreateTrainerRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCategoryPostRequest {
    @NotNull
    private Long categoryId;
    @NotNull
    private Long postId;
    @Builder
    public CreateCategoryPostRequest(Long categoryId, Long postId) {
        this.categoryId = categoryId;
        this.postId = postId;
    }

    public static CategoryPost toCategoryPost(Category category, Post post) {
        return CategoryPost.builder()
                .category(category)
                .post(post)
                .build();
    }

}
