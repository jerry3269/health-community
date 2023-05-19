package project.healthcommunity.member.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.healthcommunity.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberRequest {
    @NotBlank
    private String username;
    @NotNull
    @Min(0)
    private int age;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    @Builder
    public CreateMemberRequest(String username, int age, String loginId, String password) {
        this.username = username;
        this.age = age;
        this.loginId = loginId;
        this.password = password;
    }

    public static Member toMember(CreateMemberRequest createMemberRequest) {
        return Member.builder()
                .username(createMemberRequest.getUsername())
                .age(createMemberRequest.getAge())
                .loginId(createMemberRequest.getLoginId())
                .password(createMemberRequest.getPassword()).build();
    }

    public static CreateMemberRequest createByMember(Member member) {
        return CreateMemberRequest.builder()
                .username(member.getUsername())
                .age(member.getAge())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .build();
    }
}
