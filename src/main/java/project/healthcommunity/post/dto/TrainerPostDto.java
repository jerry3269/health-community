package project.healthcommunity.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.category.dto.CategoryDto;
import project.healthcommunity.comment.dto.MemberCommentDto;
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
    private List<MemberCommentDto> memberCommentDtoList;

    public TrainerPostDto(Post post) {
        this.trainerId = post.getTrainer().getId();
        this.trainerName = post.getTrainer().getTrainerName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.sympathy = post.getSympathy();
        this.categoryDtoList = post.getCategoryList().stream().map(CategoryDto::new).collect(toList());
        this.memberCommentDtoList = post.getComments().stream().map(MemberCommentDto::new).collect(toList());
    }
}
