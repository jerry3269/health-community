package project.healthcommunity.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMemberCommentRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long memberId;
    @NotBlank
    private String content;
}
