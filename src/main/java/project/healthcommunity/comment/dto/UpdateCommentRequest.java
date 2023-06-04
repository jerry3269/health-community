package project.healthcommunity.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCommentRequest {
    @NotNull
    private Long commentId;
    @NotBlank
    private String content;
    @Builder
    public UpdateCommentRequest(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}
