package project.healthcommunity.certificate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.dto.CertificateResponse;
import project.healthcommunity.certificate.dto.CreateCertificateRequest;
import project.healthcommunity.certificate.dto.UpdateCertificateRequest;
import project.healthcommunity.certificate.exception.CertificateUnauthorizedException;
import project.healthcommunity.certificate.exception.DupCertificateException;
import project.healthcommunity.certificate.repository.CertificateRepositoryCustom;
import project.healthcommunity.trainer.domain.TrainerSession;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepositoryCustom certificateRepositoryCustom;
    private final TrainerRepositoryCustom trainerRepositoryCustom;


    @Transactional
    public CertificateResponse register(TrainerSession trainerSession, CreateCertificateRequest createCertificateRequest) {
        validDupCertificate(trainerSession, createCertificateRequest);
        Trainer trainer = trainerRepositoryCustom.getById(trainerSession.getId());
        Certificate certificate = CreateCertificateRequest.toCertificate(trainer, createCertificateRequest);
        certificateRepositoryCustom.save(certificate);
        return new CertificateResponse(certificate);
    }

    private void validDupCertificate(TrainerSession trainerSession, CreateCertificateRequest createCertificateRequest) {
        List<Certificate> certificates = getByTrainerId(trainerSession.getId());
        for (Certificate c : certificates) {
            if (c.getCertificateName().equals(createCertificateRequest.getCertificateName())) {
                throw new DupCertificateException();
            }
        }
    }

    @Transactional
    public CertificateResponse update(TrainerSession trainerSession, UpdateCertificateRequest updateCertificateRequest){
        Certificate certificate = isValidUser(trainerSession, updateCertificateRequest.getId());
        certificate.update(updateCertificateRequest);
        return new CertificateResponse(certificate);
    }

    private Certificate isValidUser(TrainerSession trainerSession, Long certificateId) {
        Certificate certificate = getById(certificateId);
        if (!trainerSession.getId().equals(certificate.getTrainer().getId())) {
            throw new CertificateUnauthorizedException();
        }
        return certificate;
    }

    @Transactional
    public void delete(TrainerSession trainerSession, Long certificateId){
        Certificate certificate = isValidUser(trainerSession, certificateId);
        certificateRepositoryCustom.deleteById(certificate.getId());
    }

    public List<CertificateResponse> getCertificateResponseByTrainer(TrainerSession trainerSession) {
        List<Certificate> certificatesByTrainer = getByTrainerId(trainerSession.getId());
        return certificatesByTrainer.stream().map(CertificateResponse::new).collect(Collectors.toList());
    }

    private List<Certificate> getByTrainerId(Long trainerId){
        Trainer trainer = trainerRepositoryCustom.getById(trainerId);
        return trainer.getCertificates();
    }

    private Certificate getById(Long certificateId) {
       return certificateRepositoryCustom.getById(certificateId);
    }



}
