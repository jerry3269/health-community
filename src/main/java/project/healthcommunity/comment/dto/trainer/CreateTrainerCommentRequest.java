package project.healthcommunity.comment.dto.trainer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTrainerCommentRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long trainerId;
    @NotBlank
    private String content;

    @Builder
    public CreateTrainerCommentRequest(Long postId, Long trainerId, String content) {
        this.postId = postId;
        this.trainerId = trainerId;
        this.content = content;
    }
}
