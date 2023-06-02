package project.healthcommunity.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
    @Length(min = 2)
    private String content;

    private List<String> categoryNameList;

    @Builder
    public CreatePostRequest(Long trainerId, String title, String content, List<String> categoryNameList) {
        this.trainerId = trainerId;
        this.title = title;
        this.content = content;
        this.categoryNameList = categoryNameList;
    }
}
