package project.healthcommunity.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.member.domain.Member;


@Data
@AllArgsConstructor
public class CreateMemberResponse {

    private Long id;
    private String username;

    @QueryProjection
    public CreateMemberResponse(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
