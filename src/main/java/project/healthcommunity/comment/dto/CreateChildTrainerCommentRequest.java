package project.healthcommunity.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.comment.domain.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateChildTrainerCommentRequest {

    @NotNull
    private Long postId;
    @NotNull
    private Long trainerId;
    @NotBlank
    private String content;
    @NotNull
    private Long parentId;

    public CreateChildTrainerCommentRequest(Comment comment) {
        this.postId = comment.getPost().getId();
        this.trainerId = comment.getTrainer().getId();
        this.content = comment.getContent();
        this.parentId = comment.getParent().getId();
    }
}
