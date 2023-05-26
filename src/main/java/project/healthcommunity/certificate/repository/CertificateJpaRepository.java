package project.healthcommunity.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.certificate.domain.Certificate;

import java.util.List;
import java.util.Optional;

public interface CertificateJpaRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByTrainer_id(Long trainerId);
    Optional<Certificate> findById(Long certificateId);
}
