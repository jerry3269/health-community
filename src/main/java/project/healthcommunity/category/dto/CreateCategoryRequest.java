package project.healthcommunity.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank
    private String categoryName;
}
