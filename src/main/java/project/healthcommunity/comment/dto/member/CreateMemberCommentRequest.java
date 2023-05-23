package project.healthcommunity.comment.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED )
public class CreateMemberCommentRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long memberId;
    @NotBlank
    private String content;
}
