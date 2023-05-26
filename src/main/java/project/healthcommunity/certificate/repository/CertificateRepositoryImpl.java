package project.healthcommunity.certificate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.exception.CertificateNotFoundException;
import project.healthcommunity.certificate.exception.CertificateNotYetException;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CertificateRepositoryImpl implements CertificateRepositoryCustom{

    private final CertificateRepositoryCustom certificateRepositoryCustom;

    @Override
    public Certificate save(Certificate certificate) {
        return certificateRepositoryCustom.save(certificate);
    }

    @Override
    public void deleteById(Long certificateId) {
        certificateRepositoryCustom.deleteById(certificateId);
    }

    @Override
    public List<Certificate> getByTrainer_id(Long trainerId) {
        return certificateRepositoryCustom.getByTrainer_id(trainerId);
    }

    @Override
    public Certificate getById(Long certificateId) {
        return certificateRepositoryCustom.getById(certificateId);
    }
}
