package project.healthcommunity.member.dto;

import lombok.*;

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
}
