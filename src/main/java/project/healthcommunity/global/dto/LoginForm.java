package project.healthcommunity.global.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginForm {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    @Builder
    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
