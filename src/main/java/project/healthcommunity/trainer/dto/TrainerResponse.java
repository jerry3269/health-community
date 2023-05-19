package project.healthcommunity.trainer.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.certificate.dto.CertificateForm;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.domain.TrainerSession;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainerResponse {

    @NotNull
    private Long id;
    @NotBlank
    private String trainerName;
    @NotBlank
    private String loginId;
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
    protected TrainerResponse(Long id, String trainerName,String loginId, int age, int career, int likes, int certificateCount, int postCount, int commentCount) {
        this.id = id;
        this.trainerName = trainerName;
        this.loginId = loginId;
        this.age = age;
        this.career = career;
        this.likes = likes;
        this.certificateCount = certificateCount;
        this.postCount = postCount;
        this.commentCount = commentCount;
    }

    public static TrainerResponse createByTrainer(Trainer trainer) {
        return TrainerResponse.builder()
                .id(trainer.getId())
                .trainerName(trainer.getTrainerName())
                .loginId(trainer.getLoginId())
                .age(trainer.getAge())
                .career(trainer.getCareer())
                .likes(trainer.getLikes())
                .certificateCount(trainer.getCertificateCount())
                .postCount(trainer.getPostCount())
                .commentCount(trainer.getCommentCount())
                .build();
    }

    public static TrainerResponse createByTrainerSession(TrainerSession trainerSession) {
        return TrainerResponse.builder()
                .id(trainerSession.getId())
                .trainerName(trainerSession.getTrainerName())
                .loginId(trainerSession.getLoginId())
                .build();
    }

    public void setCertificateFormList(List<CertificateForm> certificateFormList) {
        this.certificateFormList = certificateFormList;
    }
}
