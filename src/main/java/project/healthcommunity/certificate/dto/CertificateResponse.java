package project.healthcommunity.certificate.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.certificate.domain.Certificate;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertificateResponse {

    @NotNull
    private Long trainerId;
    @NotBlank
    private String certificateName;
    @NotNull
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate acquiredDate;

    @QueryProjection
    public CertificateResponse(Certificate certificate) {
        this.trainerId = certificate.getTrainer().getId();
        this.certificateName = certificate.getCertificateName();
        this.acquiredDate = certificate.getAcquiredDate();
    }
}
