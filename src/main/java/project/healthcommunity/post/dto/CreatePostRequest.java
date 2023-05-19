package project.healthcommunity.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePostRequest {
    @NotNull
    private Long trainerId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private List<String> categoryNameList;
}
