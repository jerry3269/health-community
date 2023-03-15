package project.healthcommunity.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.category.dto.CategoryDto;
import project.healthcommunity.comment.dto.MemberCommentDto;
import project.healthcommunity.post.domain.Post;

import java.util.List;

import static java.util.stream.Collectors.*;

@Data
@AllArgsConstructor
public class MemberPostDto {
    private Long memberId;

    private String username;

    private String title;

    private String content;

    private int likes;

    private List<CategoryDto> categoryDtoList;
    private List<MemberCommentDto> memberCommentDtoList;

    public MemberPostDto(Post post){
        this.memberId = post.getMember().getId();
        this.username = post.getMember().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likes = post.getLikes();
        this.categoryDtoList = post.getCategoryList().stream().map(CategoryDto::new).collect(toList());
        this.memberCommentDtoList = post.getComments().stream().map(MemberCommentDto::new).collect(toList());
    }
}
