package project.healthcommunity.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.categorypost.domain.CategoryPost;

@Data
@AllArgsConstructor
public class CategoryResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String categoryName;


    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }

    public CategoryResponse(CategoryPost categoryPost) {
        this.id = categoryPost.getCategory().getId();
        this.categoryName = categoryPost.getCategory().getCategoryName();
    }
}