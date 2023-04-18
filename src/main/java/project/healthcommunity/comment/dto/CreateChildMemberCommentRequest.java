package project.healthcommunity.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.comment.domain.Comment;

@Data
@AllArgsConstructor
public class CreateChildMemberCommentRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long memberId;
    @NotBlank
    private String content;
    @NotNull
    private Long parentId;


    public CreateChildMemberCommentRequest(Comment comment) {
        this.postId = comment.getPost().getId();
        this.memberId = comment.getMember().getId();
        this.content = comment.getContent();
        this.parentId = comment.getParent().getId();
    }
}
