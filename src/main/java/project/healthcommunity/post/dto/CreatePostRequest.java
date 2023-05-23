package project.healthcommunity.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePostRequest {
    @NotNull
    private Long trainerId;
    @NotBlank
    @Length(min = 2)
    private String title;
    @NotBlank
    @Length(min = 20)
    private String content;

    private List<String> categoryNameList;
}
