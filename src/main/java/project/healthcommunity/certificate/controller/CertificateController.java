package project.healthcommunity.certificate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.dto.CertificateForm;
import project.healthcommunity.certificate.repository.CertificateRepository;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {

    private CertificateRepository certificateRepository;

    @GetMapping("/search/{trainerId}")
    public List<CertificateForm> search_trainerId(@PathVariable("trainerId") Long trainerId) {
        List<Certificate> list = certificateRepository.findByTrainer_id(trainerId);
        List<CertificateForm> certificateForms = list.stream().map(CertificateForm::new).collect(toList());
        return certificateForms;
    }
}
