package project.healthcommunity.categorypost.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateCategoryPostRequest {
    @NotNull
    private Long categoryId;
    @NotNull
    private Long postId;
}
