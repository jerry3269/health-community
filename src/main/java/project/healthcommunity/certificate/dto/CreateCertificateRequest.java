package project.healthcommunity.certificate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.trainer.domain.Trainer;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCertificateRequest {
    @NotNull
    private Long trainerId;
    @NotNull
    private String certificateName;
    @NotNull
    private LocalDate acquiredDate;

    @Builder
    public CreateCertificateRequest(Long trainerId, String certificateName, LocalDate acquiredDate) {
        this.trainerId = trainerId;
        this.certificateName = certificateName;
        this.acquiredDate = acquiredDate;
    }

    public static Certificate toCertificate(Trainer trainer, CreateCertificateRequest createCertificateRequest) {
        return Certificate.builder()
                .trainer(trainer)
                .certificateName(createCertificateRequest.getCertificateName())
                .acquiredDate(createCertificateRequest.getAcquiredDate())
                .build();
    }
}
