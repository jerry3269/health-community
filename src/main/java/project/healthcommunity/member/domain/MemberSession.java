package project.healthcommunity.member.domain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSession implements Serializable {

    private Long id;
    private String username;
    private String loginId;
    private String password;

    @Builder
    protected MemberSession(Long id, String username, String loginId, String password) {
        this.id = id;
        this.username = username;
        this.loginId = loginId;
        this.password = password;
    }

    public static MemberSession createMemberSession(Member member) {
        return MemberSession.builder()
                .id(member.getId())
                .username(member.getUsername())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .build();
    }

    public void invalidate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
