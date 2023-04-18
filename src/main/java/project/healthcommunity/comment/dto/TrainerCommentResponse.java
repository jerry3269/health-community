package project.healthcommunity.comment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
public class TrainerCommentResponse {
    @NotNull
    private Long commentId;
    @NotNull
    private Long postId;
    @NotNull
    private Long trainerId;
    @NotBlank
    private String content;
    @NotNull
    @Min(0)
    private int likes;
    private List<CommentDto> commentDtoList;


    public TrainerCommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.postId = comment.getPost().getId();
        this.trainerId = comment.getTrainer().getId();
        this.content = comment.getContent();
        this.likes = comment.getLikes();
        this.commentDtoList = comment.getChild().stream().map(CommentDto::new).collect(toList());
    }
}
