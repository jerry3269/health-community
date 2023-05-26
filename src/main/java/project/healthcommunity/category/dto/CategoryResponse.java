package project.healthcommunity.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.categorypost.domain.CategoryPost;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String categoryName;

    @Builder
    protected CategoryResponse(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public static CategoryResponse createByCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    public CategoryResponse(CategoryPost categoryPost) {
        this.id = categoryPost.getCategory().getId();
        this.categoryName = categoryPost.getCategory().getCategoryName();
    }
}
