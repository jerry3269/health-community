package project.healthcommunity.trainer.dto;

import lombok.Data;
import project.healthcommunity.certificate.dto.CertificateDto;

import java.util.List;

@Data
public class UpdateTrainerDto {

    private String trainerName;
    private List<CertificateDto> certificateDtoList;
}
