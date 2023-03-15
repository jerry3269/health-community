package project.healthcommunity.certificate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.healthcommunity.certificate.domain.Certificate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDto {

    private String certificateName;


    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate acquisitionDate;

    public CertificateDto(Certificate certificate) {
        this.certificateName = certificate.getCertificateName();
        this.acquisitionDate = certificate.getAcquisitionDate();
    }
}
