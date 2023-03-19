package project.healthcommunity.trainer.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private Long id;
    private String trainerName;
    private int age;
    private int career;
    private List<CertificateDto> certificateDtoList;

    @Builder
    public TrainerDto(Trainer trainer) {
        this.id = trainer.getId();
        this.trainerName = trainer.getTrainerName();
        this.age = trainer.getAge();
        this.career = trainer.getCareer();
        this.certificateDtoList = trainer.getCertificates().stream().map(CertificateDto::new).collect(toList());
    }
    @Builder
    @QueryProjection
    public TrainerDto(Long id, String trainerName, int age, int career) {
        this.id = id;
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
    }
}
