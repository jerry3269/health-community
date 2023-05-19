package project.healthcommunity.post.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSearchCond {
    private String title;
    private String content;
    @Min(0)
    private Integer likesGoe;
}
