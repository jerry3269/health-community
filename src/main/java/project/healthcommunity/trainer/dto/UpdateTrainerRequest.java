package project.healthcommunity.trainer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateTrainerRequest {

    @NotBlank
    private String trainerName;
    @NotBlank
    private String password;

    @Builder
    public UpdateTrainerRequest(String trainerName, String password) {
        this.trainerName = trainerName;
        this.password = password;
    }

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

}
