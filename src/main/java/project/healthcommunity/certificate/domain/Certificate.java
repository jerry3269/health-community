package project.healthcommunity.certificate.domain;

import jakarta.persistence.*;
import lombok.*;
import project.healthcommunity.certificate.dto.UpdateCertificateRequest;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.global.basic.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "certificateName", "acquiredDate"})
public class Certificate extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "certificate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    private String certificateName;

    private LocalDate acquiredDate;

    @Builder
    public Certificate(Trainer trainer, String certificateName, LocalDate acquiredDate) {
        this.trainer = trainer;
        this.certificateName = certificateName;
        this.acquiredDate = acquiredDate;
    }


    // 연관관계 메서드 시작 //
    public void addTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getCertificates().add(this);

    }

    // == 비지니스 로직 == //
    public void update(UpdateCertificateRequest updateCertificateRequest) {
        this.acquiredDate = updateCertificateRequest.getAcquiredDate();
    }

}
