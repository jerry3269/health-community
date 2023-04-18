package project.healthcommunity.trainer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTrainerRequest {

    @NotBlank
    private String trainerName;

    @NotNull
    @Min(value = 20)
    private int age;

    @NotNull
    @Min(value = 1)
    private int career;
}
