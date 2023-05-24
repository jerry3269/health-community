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
    public void deleteById(Long id) {
        certificateJpaRepository.deleteById(id);
    }

    @Override
    public List<Certificate> getByTrainer_id(Long trainerId) {
        List<Certificate> certificates = certificateJpaRepository.findByTrainer_id(trainerId);
        if(!certificates.isEmpty()){
            return certificates;
        }
        throw new CertificateNotYetException();
    }

    @Override
    public Certificate getById(Long id) {
        return certificateJpaRepository.findById(id).orElseThrow(CertificateNotFoundException::new);
    }
}
