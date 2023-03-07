package project.healthcommunity.certificate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.certificate.domain.Certificate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CertificateDto {

    private String certificateName;
    private LocalDate acquisitionDate;

    public CertificateDto(Certificate certificate) {
        this.certificateName = certificate.getCertificateName();
        this.acquisitionDate = certificate.getAcquisitionDate();
    }
}
