package project.healthcommunity.trainer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.global.domain.BaseEntity;
import project.healthcommunity.post.domain.Post;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "trainerName", "age", "career"})
public class Trainer extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "trainer_id")
    private Long id;

    private String trainerName;
    private int age;
    private int career;


    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Certificate> certificates = new ArrayList<>();

    private int ranking;

    @OneToMany(mappedBy = "trainer")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "trainer")
    private List<Comment> commentList = new ArrayList<>();

    // == 생성자 == //
    public Trainer(String trainerName, int age, int career, Certificate... certificates) {
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
        this.certificates = stream(certificates).toList();
    }

    public Trainer(String trainerName, int age, int career) {
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
    }


    // == 비지니스 로직 == //
    public void update(String trainerName, List<Certificate> certificates) {
        this.trainerName = trainerName;
        certificates.forEach(this::addCertificate);
    }

    public void addCertificate(Certificate certificate) {
        certificate.addTrainer(this);
    }

}
