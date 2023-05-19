package project.healthcommunity.trainer.domain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainerSession implements Serializable {
    private Long id;
    private String trainerName;
    private String loginId;
    private String password;

    @Builder
    protected TrainerSession(Long id, String trainerName, String loginId, String password) {
        this.id = id;
        this.trainerName = trainerName;
        this.loginId = loginId;
        this.password = password;
    }

    public static TrainerSession createByTrainer(Trainer trainer) {
        return TrainerSession.builder()
                .id(trainer.getId())
                .trainerName(trainer.getTrainerName())
                .loginId(trainer.getLoginId())
                .password(trainer.getPassword())
                .build();
    }

    public void invalidate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
