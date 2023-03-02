package project.healthcommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    // 연관관계 메서드 시작 //

    public void addTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getCertificates().add(this);
    }

    // 연관관계 메서드 끝 //


}
