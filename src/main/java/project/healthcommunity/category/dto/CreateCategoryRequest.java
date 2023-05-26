package project.healthcommunity.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import project.healthcommunity.category.domain.Category;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank
    private String categoryName;


    public static Category toCategory(CreateCategoryRequest createCategoryRequest) {
        return Category.noParentBuilder()
                .categoryName(createCategoryRequest.getCategoryName())
                .build();
    }
}
