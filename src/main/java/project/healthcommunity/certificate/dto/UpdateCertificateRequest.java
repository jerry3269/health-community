package project.healthcommunity.certificate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCertificateRequest {

    @NotNull
    private Long certificateId;
    @NotNull
    private LocalDate acquiredDate;

    @Builder
    public UpdateCertificateRequest(Long certificateId, LocalDate acquiredDate) {
        this.certificateId = certificateId;
        this.acquiredDate = acquiredDate;
    }
}
