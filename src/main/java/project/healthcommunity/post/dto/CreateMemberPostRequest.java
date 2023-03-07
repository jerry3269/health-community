package project.healthcommunity.post.dto;

import lombok.Data;
import project.healthcommunity.category.dto.CategoryDto;
import project.healthcommunity.categorypost.dto.CategoryPostDto;

import java.util.List;

@Data
public class CreateMemberPostRequest {
    private Long memberId;

    private String username;

    private String title;

    private String content;

    private List<String> categoryNameList;

}
