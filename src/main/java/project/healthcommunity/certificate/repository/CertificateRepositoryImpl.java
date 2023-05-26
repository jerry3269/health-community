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

    private final CertificateJpaRepository certificateJpaRepository;

    @Override
    public Certificate save(Certificate certificate) {
        return certificateJpaRepository.save(certificate);
    }

    @Override
    public void deleteById(Long certificateId) {
        certificateJpaRepository.deleteById(certificateId);
    }

    @Override
    public List<Certificate> getByTrainer_id(Long trainerId) {
        return certificateJpaRepository.findByTrainer_id(trainerId).orElseThrow(CertificateNotYetException::new);
    }

    @Override
    public Certificate getById(Long certificateId) {
        return certificateJpaRepository.findById(certificateId).orElseThrow(CertificateNotFoundException::new);
    }
}
