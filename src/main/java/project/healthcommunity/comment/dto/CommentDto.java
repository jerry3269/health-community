package project.healthcommunity.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

@Data
@AllArgsConstructor
public class CommentDto {
    @NotNull
    private Long commentId;
    @NotNull
    private Long postId;
    @NotBlank
    private String content;
    @NotNull
    @Min(0)
    private int likes;

    @QueryProjection
    public CommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
        this.likes = comment.getLikes();
    }
}
