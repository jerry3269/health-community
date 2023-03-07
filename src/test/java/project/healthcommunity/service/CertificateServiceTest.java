package project.healthcommunity.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.service.CertificateService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.certificate.repository.CertificateRepository;
import project.healthcommunity.trainer.service.TrainerService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CertificateServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    TrainerService trainerService;

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    CertificateService certificateService;


    @AfterEach
    void clear(){
        trainerService.clear();
        certificateRepository.deleteAll();
    }


    @Test
    void join() {
        Trainer trainer1 = new Trainer("t1",10,1);


        Certificate certificate1 = new Certificate(trainer1, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer1, "윗몸1급", LocalDate.of(2013,1,1));
        trainerService.join(trainer1);

        Trainer findTrainer = trainerService.findOne(trainer1.getId());

        assertThat(findTrainer).isEqualTo(trainer1);

        List<Certificate> certificates = certificateService.findCertificatesByTrainer(trainer1.getId());

        for (Certificate certificate : certificates) {
            System.out.println("certificate = " + certificate);
        }
    }

    @Test
    void deleteCertificate() {
        Trainer trainer = new Trainer("t1",10,1);

        Certificate certificate1 = new Certificate(trainer, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer, "윗몸1급", LocalDate.of(2020,1,1));
        trainerService.join(trainer);
        em.flush();
        em.clear();


        certificateService.deleteCertificate(trainer.getId(), "팔굽1급");


    }

    @Test
    void addCertificate() {
        Trainer trainer = new Trainer("t1",10,1);

        Certificate certificate1 = new Certificate(trainer, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer, "윗몸1급", LocalDate.of(2020,1,1));
        trainerService.join(trainer);
        em.flush();
        em.clear();


        Certificate certificate3 = new Certificate(trainer, "체력1급", LocalDate.of(2020,1,1));
//        Certificate certificate4 = new Certificate(trainer, "윗몸1급", LocalDate.of(2020,1,1));

        certificateService.addCertificate(trainer.getId(), certificate3);
    }

    @Test
    void updateCertificate() {
        Trainer trainer = new Trainer("t1",10,1);

        Certificate certificate1 = new Certificate(trainer, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer, "윗몸1급", LocalDate.of(2020,1,1));
        trainerService.join(trainer);
        em.flush();
        em.clear();

        certificateService.updateCertificate(trainer.getId(), certificate1, "체력1급", LocalDate.of(2011,12,19));
    }

    @Test
    void findCertificatesByTrainer() {
        Trainer trainer1 = new Trainer("t1",10,1);
        Trainer trainer2 = new Trainer("t2",20,2);

        Certificate certificate1 = new Certificate(trainer1, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer1, "윗몸1급", LocalDate.of(2020,1,1));
        Certificate certificate3 = new Certificate(trainer2, "체력1급", LocalDate.of(2020,1,1));
        trainerService.join(trainer1);
        trainerService.join(trainer2);
        em.flush();
        em.clear();

        List<Certificate> certificatesByTrainer1 = certificateService.findCertificatesByTrainer(trainer1.getId());

        for (Certificate certificate : certificatesByTrainer1) {
            System.out.println("certificate = " + certificate);
        }


    }

    @Test
    void findCertificateByTrainer() {
        Trainer trainer1 = new Trainer("t1",10,1);
        Trainer trainer2 = new Trainer("t2",20,2);

        Certificate certificate1 = new Certificate(trainer1, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer1, "윗몸1급", LocalDate.of(2020,1,1));
        Certificate certificate3 = new Certificate(trainer2, "체력1급", LocalDate.of(2020,1,1));
        trainerService.join(trainer1);
        trainerService.join(trainer2);
        em.flush();
        em.clear();

        Certificate findCertificate1 = certificateService.findCertificateByTrainer(trainer1.getId(), "팔굽1급");
        Certificate findCertificate2 = certificateService.findCertificateByTrainer(trainer2.getId(), "체력1급");

        System.out.println("findCertificate1 = " + findCertificate1);
        System.out.println("findCertificate2 = " + findCertificate2);
    }

    @Test
    void DupCertificateTest(){
        Trainer trainer1 = new Trainer("t1",10,1);
        Trainer trainer2 = new Trainer("t2",10,1);

        Certificate certificate1 = new Certificate(trainer1, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer2, "팔굽1급", LocalDate.of(2020,1,1));
        trainerService.join(trainer1);
        trainerService.join(trainer2);

        assertThrows(IllegalStateException.class, () -> {
            certificateService.addCertificate(trainer1.getId(),new Certificate(trainer1, "팔굽1급", LocalDate.of(2019,12,19)));
        });
    }

    @Test
    void deleteTrainer() {
        Trainer trainer1 = new Trainer("t1",10,1);
        Trainer trainer2 = new Trainer("t2",10,1);

        Certificate certificate1 = new Certificate(trainer1, "팔굽1급", LocalDate.of(2019,12,19));
        Certificate certificate2 = new Certificate(trainer2, "팔굽1급", LocalDate.of(2020,1,1));
        trainerService.join(trainer1);
        trainerService.join(trainer2);

        em.flush();
        em.clear();

        List<Trainer> trainers1 = trainerService.trainers();

        for (Trainer trainer : trainers1) {
            System.out.println("trainer = " + trainer);
            for (Certificate certificate : trainer.getCertificates()) {
                System.out.println("certificate = " + certificate);
            }
        }

        trainerService.deleteTrainer(trainer1.getId());

        em.flush();
        em.clear();

        List<Trainer> trainers2 = trainerService.trainers();

        for (Trainer trainer : trainers2) {
            System.out.println("trainer = " + trainer);
            for (Certificate certificate : trainer.getCertificates()) {
                System.out.println("certificate = " + certificate);
            }
        }
    }
}