package project.healthcommunity.comment.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED )
public class CreateMemberCommentRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long memberId;
    @NotBlank
    private String content;

    @Builder
    public CreateMemberCommentRequest(Long postId, Long memberId, String content) {
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
    }
}
