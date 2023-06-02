package project.healthcommunity.post.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSearchCond {
    private String title;
    private String content;
    @Min(0)
    private Integer likesGoe;

    @Builder
    public PostSearchCond(String title, String content, Integer likesGoe) {
        this.title = title;
        this.content = content;
        this.likesGoe = likesGoe;
    }
}
