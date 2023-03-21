package project.healthcommunity.trainer.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.certificate.dto.CertificateDto;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.List;

@Data
public class TrainerResult {
    private Long id;
    private String trainerName;
    private int age;
    private int career;

    private List<CertificateDto> certificateDtoList;
    private int likes;
    private int certificateCount;
    private int postCount;
    private int commentCount;

    @Builder
    @QueryProjection
    public TrainerResult(Long id, String trainerName, int age, int career, int likes, int certificateCount, int postCount, int commentCount) {
        this.id = id;
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
        this.likes = likes;
        this.certificateCount = certificateCount;
        this.postCount = postCount;
        this.commentCount = commentCount;
    }

    public TrainerResult(Trainer trainer) {
        this.id = trainer.getId();
        this.trainerName = trainer.getTrainerName();
        this.age = trainer.getAge();
        this.career = trainer.getCareer();
        this.likes = trainer.getLikes();
        this.certificateCount = trainer.getCertificateCount();
        this.postCount = trainer.getPostCount();
        this.commentCount = trainer.getCommentCount();
    }


}
