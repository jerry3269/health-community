package project.healthcommunity.post.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.category.dto.CategoryResponse;
import project.healthcommunity.comment.dto.CommentDto;
import project.healthcommunity.post.domain.Post;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
public class TrainerPostResponse {
    @NotNull
    private Long trainerId;
    @NotBlank
    private String trainerName;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    @Min(0)
    private int likes;

    private List<CategoryResponse> categoryResponseList;
    private List<CommentDto> CommentDtoList;

    public TrainerPostResponse(Post post) {
        this.trainerId = post.getTrainer().getId();
        this.trainerName = post.getTrainer().getTrainerName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likes = post.getLikes();
        this.categoryResponseList = post.getCategoryList().stream().map(CategoryResponse::new).collect(toList());
        this.CommentDtoList = post.getComments().stream().map(CommentDto::new).collect(toList());
    }
}
