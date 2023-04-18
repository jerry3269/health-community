package project.healthcommunity.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTrainerCommentRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long trainerId;
    @NotBlank
    private String content;
}
