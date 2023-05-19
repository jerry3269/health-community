package project.healthcommunity.trainer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.dto.CertificateForm;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateTrainerRequest {

    @NotBlank
    private String trainerName;
    @NotBlank
    private String password;

    @Builder
    public UpdateTrainerRequest(String trainerName, String password) {
        this.trainerName = trainerName;
        this.password = password;
    }

}
