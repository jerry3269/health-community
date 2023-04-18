package project.healthcommunity.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.List;

@Data
public class CreatePostRequest {
    @NotNull
    private Long trainerId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private List<String> categoryNameList;
}
