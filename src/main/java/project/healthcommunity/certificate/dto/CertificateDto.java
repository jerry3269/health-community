package project.healthcommunity.certificate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import project.healthcommunity.certificate.domain.Certificate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDto {

    private Long trainerId;

    private String certificateName;


    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate acquisitionDate;

    @QueryProjection
    public CertificateDto(Certificate certificate) {
        this.trainerId = certificate.getTrainer().getId();
        this.certificateName = certificate.getCertificateName();
        this.acquisitionDate = certificate.getAcquisitionDate();
    }
}
