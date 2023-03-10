package project.healthcommunity.categorypost.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.categorypost.domain.CategoryPost;

@Data
@AllArgsConstructor
public class CategoryPostDto {

    private Long categoryId;
    private String categoryName;
    private Long postId;
    private String postTitle;

    public CategoryPostDto(CategoryPost categoryPost) {
        this.categoryId = categoryPost.getCategory().getId();
        this.categoryName = categoryPost.getCategory().getCategoryName();
        this.postId = categoryPost.getPost().getId();
        this.postTitle = categoryPost.getPost().getTitle();
    }

}
