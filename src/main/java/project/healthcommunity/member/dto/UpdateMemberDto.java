package project.healthcommunity.member.dto;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMemberDto {
    private String username;
    private String password;

    @Builder
    public UpdateMemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
