package project.healthcommunity.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

import java.util.List;

import static java.util.stream.Collectors.*;

@Data
@AllArgsConstructor
public class MemberCommentDto {
    private Long postId;
    private String postTitle;

    private String username;
    private String content;
    private int likes;

    private List<ChildCommentDto> childCommentDtoList;


    public MemberCommentDto(Comment comment) {
        this.postId = comment.getPost().getId();
        this.postTitle = comment.getPost().getTitle();
        this.username = comment.getMember().getUsername();
        this.content = comment.getContent();
        this.likes = comment.getLikes();
        childCommentDtoList = comment.getChild().stream().map(ChildCommentDto::new).collect(toList());
    }
}
