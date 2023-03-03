package project.healthcommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.domain.Certificate;
import project.healthcommunity.repository.CertificateRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;

    @Transactional
    public Certificate join(Certificate certificate) {
        certificateRepository.save(certificate);
        return certificate;
    }



}
