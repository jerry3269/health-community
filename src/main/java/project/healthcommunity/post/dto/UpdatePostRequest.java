package project.healthcommunity.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
