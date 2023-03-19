package project.healthcommunity.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

@Data
public class CommentDto {
    private Long postId;
    private String postTitle;
    private String content;
    private int likes;
    @Builder
    @QueryProjection
    public CommentDto(Comment comment) {
        this.postId = comment.getPost().getId();
        this.postTitle = comment.getPost().getTitle();
        this.content = comment.getContent();
        this.likes = comment.getLikes();
    }
}
