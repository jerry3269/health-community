package project.healthcommunity.trainer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import project.healthcommunity.certificate.dto.CertificateForm;

import java.util.List;

@Data
public class UpdateTrainerRequest {

    @NotBlank
    private String trainerName;

    private List<CertificateForm> certificateFormList;
}
