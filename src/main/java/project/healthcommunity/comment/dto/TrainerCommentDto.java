package project.healthcommunity.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
public class TrainerCommentDto {
    private Long postId;
    private String postTitle;

    private String trainerName;
    private String content;
    private int sympathy;

    private List<ChildCommentDto> childCommentDtoList;


    public TrainerCommentDto(Comment comment) {
        this.postId = comment.getPost().getId();
        this.postTitle = comment.getPost().getTitle();
        this.trainerName = comment.getTrainer().getTrainerName();
        this.content = comment.getContent();
        this.sympathy = comment.getSympathy();
        childCommentDtoList = comment.getChild().stream().map(ChildCommentDto::new).collect(toList());
    }
}
