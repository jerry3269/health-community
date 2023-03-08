package project.healthcommunity.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Data
@AllArgsConstructor
public class MemberCommentDto {
    private Long postId;
    private String postTitle;

    private String username;
    private String content;
    private int sympathy;

    private List<ChildCommentDto> childCommentDtoList;


    public MemberCommentDto(Comment comment) {
        this.postId = comment.getPost().getId();
        this.postTitle = comment.getPost().getTitle();
        this.username = comment.getMember().getUsername();
        this.content = comment.getContent();
        this.sympathy = comment.getSympathy();
        childCommentDtoList = comment.getChild().stream().map(ChildCommentDto::new).collect(toList());
    }
}
