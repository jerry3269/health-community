package project.healthcommunity.trainer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.trainer.domain.Trainer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTrainerRequest {

    @NotBlank
    private String trainerName;

    @NotNull
    @Min(value = 20)
    private int age;

    @NotNull
    @Min(value = 1)
    private int career;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    @Builder
    public CreateTrainerRequest(String trainerName, int age, int career, String loginId, String password) {
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
        this.loginId = loginId;
        this.password = password;
    }

    public static Trainer toTrainer(CreateTrainerRequest createTrainerRequest) {
        return Trainer.noCertificateBuilder()
                .trainerName(createTrainerRequest.trainerName)
                .age(createTrainerRequest.age)
                .career(createTrainerRequest.career)
                .loginId(createTrainerRequest.loginId)
                .password(createTrainerRequest.password).build();
    }
}
