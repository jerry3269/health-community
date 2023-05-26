package project.healthcommunity.certificate.repository;

import project.healthcommunity.certificate.domain.Certificate;

import java.util.List;

public interface CertificateRepositoryCustom {
    Certificate save(Certificate certificate);

    void deleteById(Long certificateId);

    List<Certificate> getByTrainer_id(Long trainerId);

    Certificate getById(Long certificateId);
}
