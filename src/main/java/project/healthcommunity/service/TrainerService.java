package project.healthcommunity.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.domain.Certificate;
import project.healthcommunity.domain.Trainer;
import project.healthcommunity.repository.CertificateRepository;
import project.healthcommunity.repository.TrainerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final CertificateRepository certificateRepository;

    /**
     * 트레이너 등록
     */
    @Transactional
    public void join(Trainer trainer){
        validDupTrainer(trainer);
        trainerRepository.save(trainer);
    }

    /**
     * 중복 트레이너 검사
     */
    private void validDupTrainer(Trainer trainer) {
        List<Trainer> findTrainer = trainerRepository.findByTrainerName(trainer.getTrainerName());
        if(!findTrainer.isEmpty()){
            throw new IllegalStateException("이미 존재하는 트레이너입니다.");
        }
    }

    /**
     * 정보 수정
     */
    @Transactional
    public void update(Long id, String trainerName, int age, int career){
        Trainer findTrainer = findOne(id);
        findTrainer.update(trainerName, age, career);
    }

    /**
     * 조회
     */
    public Trainer findOne(Long id){
        Optional<Trainer> findTrainer = trainerRepository.findById(id);
        if(!findTrainer.isPresent()){
            throw new IllegalStateException("존재하지 않는 트레이너입니다.");
        }
        return findTrainer.get();
    }

    /**
     * 전체 조회
     */
    public List<Trainer> trainers(){
        return trainerRepository.findAll();
    }

    /**
     * 트레이너 삭제
     */
    @Transactional
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    /**
     * 자격증 등록
     */
    @Transactional
    public void addCertificate(Long id, Certificate certificate){
        Trainer findTrainer = findOne(id);
        List<Certificate> certificates = findCertificatesByTrainer(id);
        validDupCertificate(certificates, certificate);
        findTrainer.addCertificate(certificate);
        certificateRepository.save(certificate);
    }

    /**
     * 중복 자격증 검사
     */
    private void validDupCertificate(List<Certificate> certificates, Certificate certificate) {
        certificates.stream().forEach(c -> {
            if(c.getCertificateName() == certificate.getCertificateName()){
                throw new IllegalStateException("이미 해당 자격증이 있습니다.");
            }
        });
    }

    /**
     * 자격증 수정
     */
    @Transactional
    public void updateCertificate(Long id, Certificate certificate, String certificateName, LocalDate acquisitionDate){
        Certificate findCertificate = findCertificateByTrainer(id, certificate.getCertificateName());
        findCertificate.update(certificateName, acquisitionDate);
    }

    /**
     * 자격증 삭제
     */
    @Transactional
    public void deleteCertificate(Long id, Certificate certificate){
        Certificate findCertificate = findCertificateByTrainer(id, certificate.getCertificateName());
        certificateRepository.delete(findCertificate);
    }

    /**
     * 해당 트레이너의 자격증 조회
     */
    public List<Certificate> findCertificatesByTrainer(Long id){
        Trainer findTrainer = findOne(id);
        return findTrainer.getCertificates();
    }

    /**
     * 자격증 하나 조회
     */
    public Certificate findCertificateByTrainer(Long id, String certificateName) {
        Certificate findCertificate = certificateRepository.findByTrainer_idAndCertificateName(id, certificateName);
        return findCertificate;
    }
}
