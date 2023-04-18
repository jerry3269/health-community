package project.healthcommunity.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import project.healthcommunity.post.domain.Post;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @Min(0)
    private int likes;
    @NotNull
    @Min(0)
    private int commentCount;

    @QueryProjection
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.likes = post.getLikes();
        commentCount = post.getComments().size();
    }
}