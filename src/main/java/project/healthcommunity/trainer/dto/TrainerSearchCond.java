package project.healthcommunity.trainer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainerSearchCond {
    private String trainerName;

    @Min(value = 0)
    private Integer ageGoe;

    @Min(value = 0)
    private Integer careerGoe;

    @Min(value = 0)
    private Integer certificateCountGoe;

    @Min(value = 0)
    private Integer likesGoe;

    @Min(value = 0)
    private Integer postCountGoe;

    @Min(value = 0)
    private Integer commentCountGoe;

}
