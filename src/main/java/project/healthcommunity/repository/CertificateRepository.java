package project.healthcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.domain.Certificate;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByCertificateName(String certificateName);

    Certificate findByTrainer_idAndCertificateName(Long id, String certificateName);
}
