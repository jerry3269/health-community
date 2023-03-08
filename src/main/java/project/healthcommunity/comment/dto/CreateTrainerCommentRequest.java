package project.healthcommunity.comment.dto;

import lombok.Data;

@Data
public class CreateTrainerCommentRequest {
    private Long postId;
    private String postTitle;
    private String TrainerName;
    private String content;
    private int sympathy;
}
