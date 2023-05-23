package project.healthcommunity.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.comment.domain.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateChildMemberCommentRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long memberId;
    @NotBlank
    private String content;
    @NotNull
    private Long parentId;
    @Builder
    public CreateChildMemberCommentRequest(Long postId, Long memberId, String content, Long parentId) {
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
        this.parentId = parentId;
    }

}
