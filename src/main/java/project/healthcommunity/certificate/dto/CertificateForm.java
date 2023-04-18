package project.healthcommunity.certificate.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.certificate.domain.Certificate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateForm {

    @NotNull
    private Long trainerId;
    @NotBlank
    private String certificateName;
    @NotNull
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate acquisitionDate;

    @QueryProjection
    public CertificateForm(Certificate certificate) {
        this.trainerId = certificate.getTrainer().getId();
        this.certificateName = certificate.getCertificateName();
        this.acquisitionDate = certificate.getAcquisitionDate();
    }
}
