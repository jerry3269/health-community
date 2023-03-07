package project.healthcommunity.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.healthcommunity.member.domain.Member;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private int age;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.age = member.getAge();
    }
}
