package project.healthcommunity.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import project.healthcommunity.post.domain.Post;

@Getter
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
    @Builder
    protected PostResponse(Long id, String title, int likes, int commentCount) {
        this.id = id;
        this.title = title;
        this.likes = likes;
        this.commentCount = commentCount;
    }


    @QueryProjection
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.likes = post.getLikes();
        this.commentCount = post.getComments().size();
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
