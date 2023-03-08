package project.healthcommunity.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

@Data
@AllArgsConstructor
public class ChildCommentDto {
    private Long postId;
    private String postTitle;
    private String content;
    private int sympathy;

    public ChildCommentDto(Comment comment) {
        this.postId = comment.getPost().getId();
        this.postTitle = comment.getPost().getTitle();
        this.content = comment.getContent();
        this.sympathy = comment.getSympathy();
    }
}
