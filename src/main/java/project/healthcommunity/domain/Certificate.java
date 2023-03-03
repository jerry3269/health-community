package project.healthcommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "certificateName", "acquisitionDate"})
public class Certificate extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "certificate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    private String certificateName;

    private LocalDate acquisitionDate;

    // 생성자 //
    public Certificate(Trainer trainer, String certificateName, LocalDate acquisitionDate) {
        validDupCertificate(trainer.getCertificates(), certificateName);
        addTrainer(trainer);
        this.certificateName = certificateName;
        this.acquisitionDate = acquisitionDate;
    }

    // 중복 자격증 생성 제약 //
    private void validDupCertificate(List<Certificate> certificates, String certificateName) {
        certificates.stream().forEach(c -> {
            if(c.getCertificateName() == certificateName){
                throw new IllegalStateException("이미 존재하는 자격증을 추가하셨습니다.");
            }
        });
    }

    // 연관관계 메서드 시작 //
    public void addTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getCertificates().add(this);
    }
    // 연관관계 메서드 끝 //

    // == 비지니스 로직 == //
    public void update(String certificateName, LocalDate acquisitionDate) {
        this.certificateName = certificateName;
        this.acquisitionDate = acquisitionDate;
    }

}
