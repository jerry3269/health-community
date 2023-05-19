package project.healthcommunity.member.dto;

import lombok.*;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.domain.MemberSession;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {
    private String username;
    private String loginId;
    private int age;
    private int commentCount;

    @Builder
    protected MemberResponse(String username, String loginId, int age, int commentCount) {
        this.username = username;
        this.loginId = loginId;
        this.age = age;
        this.commentCount = commentCount;
    }

    public static MemberResponse createByMember(Member member) {
        return MemberResponse.builder()
                .username(member.getUsername())
                .loginId(member.getLoginId())
                .age(member.getAge())
                .commentCount(member.getCommentCount())
                .build();
    }

    public static MemberResponse createByMemberSession(MemberSession memberSession) {
        return MemberResponse.builder()
                .username(memberSession.getUsername())
                .loginId(memberSession.getLoginId())
                .build();
    }



}
