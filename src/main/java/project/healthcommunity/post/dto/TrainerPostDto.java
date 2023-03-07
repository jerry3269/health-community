package project.healthcommunity.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.category.dto.CategoryDto;
import project.healthcommunity.categorypost.dto.CategoryPostDto;
import project.healthcommunity.comment.dto.CommentDto;
import project.healthcommunity.post.domain.Post;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
public class TrainerPostDto {
    private Long trainerId;

    private String trainerName;

    private String title;

    private String content;

    private int sympathy;

    private List<CategoryDto> categoryDtoList;
    private List<CommentDto> commentDtoList;

    public TrainerPostDto(Post post) {
        this.trainerId = post.getTrainer().getId();
        this.trainerName = post.getTrainer().getTrainerName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.sympathy = post.getSympathy();
        this.categoryDtoList = post.getCategoryList().stream().map(CategoryDto::new).collect(toList());
        this.commentDtoList = post.getComments().stream().map(CommentDto::new).collect(toList());
    }
}
