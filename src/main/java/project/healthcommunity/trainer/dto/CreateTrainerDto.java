package project.healthcommunity.trainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.trainer.domain.Trainer;

@Data
public class CreateTrainerDto {

    private String trainerName;
    private int age;
    private int career;
}
