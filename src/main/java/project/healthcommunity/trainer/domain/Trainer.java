package project.healthcommunity.trainer.domain;

import jakarta.persistence.*;
import lombok.*;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.global.basic.BaseEntity;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.trainer.dto.UpdateTrainerRequest;

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

    private String loginId;
    private String password;


    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Certificate> certificates = new ArrayList<>();
    private int likes = 0;

    @OneToMany(mappedBy = "trainer")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "trainer")
    private List<Comment> commentList = new ArrayList<>();

    private int certificateCount = 0;
    private int postCount = 0;
    private int commentCount = 0;

    // == 생성자 == //

    @Builder(builderMethodName = "noCertificateBuilder", builderClassName = "noCertificateBuilder")
    public Trainer(String trainerName, int age, int career, String loginId, String password) {
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
        this.loginId = loginId;
        this.password = password;
        resetCountParameters();
    }

    @Builder(builderMethodName = "certificateBuilder", builderClassName = "certificateBuilder")
    public Trainer(String trainerName, int age, int career,String loginId, String password, Certificate... certificates) {
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
        this.loginId = loginId;
        this.password = password;
        this.certificates = stream(certificates).toList();
        resetCountParameters();
    }



    // == 비지니스 로직 == //
    public void update(UpdateTrainerRequest updateTrainerRequest) {
        this.trainerName = updateTrainerRequest.getTrainerName();
        this.password = updateTrainerRequest.getPassword();
    }

    public void addCertificate(Certificate certificate) {
        certificate.addTrainer(this);
        resetCountParameters();
    }

    public void upLikes() {
        this.likes++;
    }

    public void resetCountParameters(){
        this.certificateCount = this.certificates.size();
        this.postCount = this.postList.size();
        this.commentCount = this.commentList.size();
    }





}
