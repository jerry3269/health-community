package project.healthcommunity.trainer.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainerSearchCond {
    private String trainerName;
    private Integer ageGoe;
    private Integer careerGoe;
    private Integer certificateCountGoe;
    private Integer likesGoe;
    private Integer postCountGoe;
    private Integer commentCountGoe;

}
