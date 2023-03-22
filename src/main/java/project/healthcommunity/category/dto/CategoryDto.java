package project.healthcommunity.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.categorypost.domain.CategoryPost;

@Data
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String categoryName;


    public CategoryDto(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }

    public CategoryDto(CategoryPost categoryPost) {
        this.id = categoryPost.getCategory().getId();
        this.categoryName = categoryPost.getCategory().getCategoryName();
    }
}
