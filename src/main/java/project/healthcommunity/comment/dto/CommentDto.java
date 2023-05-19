package project.healthcommunity.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.comment.domain.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
