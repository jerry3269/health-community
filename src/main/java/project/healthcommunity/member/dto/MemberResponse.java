package project.healthcommunity.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import project.healthcommunity.member.domain.Member;

@Data
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String username;
    private int age;
    private int commentCount;
    @Builder
    @QueryProjection
    public MemberResponse(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.age = member.getAge();
        this.commentCount = member.getCommentCount();
    }

}
