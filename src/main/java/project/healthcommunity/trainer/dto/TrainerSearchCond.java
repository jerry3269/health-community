package project.healthcommunity.trainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TrainerSearchCond {
    private String trainerName;
    private Integer ageGoe;
    private Integer careerGoe;
    private Integer certificateCountGoe;
    private Integer likesGoe;
    private Integer postCountGoe;
    private Integer commentCountGoe;
}
