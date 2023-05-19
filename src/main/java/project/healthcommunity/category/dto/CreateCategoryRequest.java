package project.healthcommunity.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank
    private String categoryName;
}
