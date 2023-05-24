package project.healthcommunity.certificate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCertificateRequest {

    @NotNull
    private Long id;
    @NotNull
    private LocalDate acquiredDate;
}
