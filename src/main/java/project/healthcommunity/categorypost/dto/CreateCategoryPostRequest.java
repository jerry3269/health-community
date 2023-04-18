package project.healthcommunity.categorypost.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCategoryPostRequest {
    @NotNull
    private Long categoryId;
    @NotNull
    private Long postId;
}
