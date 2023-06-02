package project.healthcommunity.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePostRequest {

    private Long postId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Builder
    public UpdatePostRequest(Long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }
}
