package project.healthcommunity.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import project.healthcommunity.member.domain.Member;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {
    private Long id;
    private String username;
    private int age;
    private int commentCount;

    @Builder
    protected MemberResponse(Long id, String username, int age, int commentCount) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.commentCount = commentCount;
    }

    @QueryProjection
    public MemberResponse(Member member) {
        return Member
    }



}
