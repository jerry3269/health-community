package project.healthcommunity.certificate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.trainer.service.TrainerService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.certificate.repository.CertificateRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;

    private final TrainerService trainerService;


    @Transactional
    public void register(Certificate certificate) {
        certificateRepository.save(certificate);
    }


    @Transactional
    public void addCertificate(Long id, Certificate certificate){
        Trainer findTrainer = trainerService.findOne(id);
        List<Certificate> certificates = findCertificatesByTrainer(id);
        validDupCertificate(certificates, certificate);
        findTrainer.addCertificate(certificate);
        register(certificate);
    }


    private void validDupCertificate(List<Certificate> certificates, Certificate certificate) {
        certificates.stream().forEach(c -> {
            if(c.getCertificateName() == certificate.getCertificateName()){
                throw new IllegalStateException("이미 해당 자격증이 있습니다.");
            }
        });
    }


    @Transactional
    public void updateCertificate(Long id, Certificate certificate, String certificateName, LocalDate acquisitionDate){
        Certificate findCertificate = findCertificateByTrainer(id, certificate.getCertificateName());
        findCertificate.update(certificateName, acquisitionDate);
    }


    @Transactional
    public void deleteCertificate(Long id, String certificateName){
        Certificate findCertificate = findCertificateByTrainer(id, certificateName);
        certificateRepository.delete(findCertificate);
    }


    public List<Certificate> findCertificatesByTrainer(Long id){
        Trainer findTrainer = trainerService.findOne(id);
        return findTrainer.getCertificates();
    }


    public Certificate findCertificateByTrainer(Long id, String certificateName) {
        Certificate findCertificate = certificateRepository.findByTrainer_idAndCertificateName(id, certificateName);
        return findCertificate;
    }


}
