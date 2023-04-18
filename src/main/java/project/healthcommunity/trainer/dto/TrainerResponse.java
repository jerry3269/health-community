package project.healthcommunity.trainer.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.certificate.dto.CertificateForm;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.List;

@Data
public class TrainerResponse {

    @NotNull
    private Long id;
    @NotBlank
    private String trainerName;
    @Min(value = 0)
    private int age;
    @Min(value = 0)
    private int career;

    private List<CertificateForm> certificateFormList;
    @Min(value = 0)
    private int likes;
    @Min(value = 0)
    private int certificateCount;
    @Min(value = 0)
    private int postCount;
    @Min(value = 0)
    private int commentCount;

    @Builder
    @QueryProjection
    public TrainerResponse(Long id, String trainerName, int age, int career, int likes, int certificateCount, int postCount, int commentCount) {
        this.id = id;
        this.trainerName = trainerName;
        this.age = age;
        this.career = career;
        this.likes = likes;
        this.certificateCount = certificateCount;
        this.postCount = postCount;
        this.commentCount = commentCount;
    }

    public TrainerResponse(Trainer trainer) {
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
