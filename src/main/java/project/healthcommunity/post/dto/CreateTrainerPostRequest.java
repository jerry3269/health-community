package project.healthcommunity.post.dto;

import lombok.Data;
import project.healthcommunity.category.dto.CategoryDto;


import java.util.List;

@Data
public class CreateTrainerPostRequest {
    private Long trainerId;

    private String trainerName;

    private String title;

    private String content;

    private List<String> categoryNameList;
}
