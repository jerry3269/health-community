package project.healthcommunity.comment.dto;

import lombok.Data;

@Data
public class CreateMemberCommentRequest {
    private Long postId;
    private String postTitle;
    private String username;
    private String content;
    private int sympathy;
}
