package project.healthcommunity.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.post.domain.Post;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
    }
}
