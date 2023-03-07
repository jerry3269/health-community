package project.healthcommunity.trainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.certificate.dto.CertificateDto;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Data
@AllArgsConstructor
public class TrainerDto {

    private String trainerName;
    private int age;
    private int career;
    private List<CertificateDto> certificateDtoList;

    public TrainerDto(Trainer trainer) {
        this.trainerName = trainer.getTrainerName();
        this.age = trainer.getAge();
        this.career = trainer.getCareer();
        this.certificateDtoList = trainer.getCertificates().stream().map(CertificateDto::new).collect(toList());
    }
}
