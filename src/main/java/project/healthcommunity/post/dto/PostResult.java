package project.healthcommunity.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import project.healthcommunity.category.dto.CategoryDto;
import project.healthcommunity.categorypost.dto.CategoryPostDto;
import project.healthcommunity.post.domain.Post;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResult {
    private Long id;
    private String title;
    private int likes;

    private int commentCount;

    @QueryProjection
    public PostResult(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.likes = post.getLikes();
    }
}
