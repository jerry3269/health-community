package project.healthcommunity.certificate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.dto.CertificateResponse;
import project.healthcommunity.certificate.dto.CreateCertificateRequest;
import project.healthcommunity.certificate.dto.UpdateCertificateRequest;
import project.healthcommunity.certificate.repository.CertificateJpaRepository;
import project.healthcommunity.certificate.repository.CertificateRepositoryCustom;
import project.healthcommunity.certificate.service.CertificateService;
import project.healthcommunity.global.controller.LoginForMember;
import project.healthcommunity.global.controller.LoginForTrainer;
import project.healthcommunity.global.exception.BindingException;
import project.healthcommunity.trainer.domain.TrainerSession;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping("/list")
    public ResponseEntity search(@LoginForTrainer TrainerSession trainerSession) {
        List<CertificateResponse> certificateResponseByTrainer = certificateService.getCertificateResponseByTrainer(trainerSession);
        return ResponseEntity.ok().body(certificateResponseByTrainer);
    }

    @PostMapping("/add")
    public ResponseEntity add(@LoginForTrainer TrainerSession trainerSession,
                              @RequestBody @Valid CreateCertificateRequest createCertificateRequest) {
        CertificateResponse certificateResponse = certificateService.register(trainerSession, createCertificateRequest);
        return ResponseEntity.ok().body(certificateResponse);
    }

    @PatchMapping("/update")
    public ResponseEntity update(@LoginForTrainer TrainerSession trainerSession,
                                 @RequestBody @Valid UpdateCertificateRequest updateCertificateRequest) {
        CertificateResponse certificateResponse = certificateService.update(trainerSession, updateCertificateRequest);
        return ResponseEntity.ok().body(certificateResponse);
    }

    @DeleteMapping("/delete/{certificateId}")
    public ResponseEntity delete(@LoginForTrainer TrainerSession trainerSession,
                                 @PathVariable Long certificateId) {
        certificateService.delete(trainerSession, certificateId);
        return ResponseEntity.ok().build();
    }
}
