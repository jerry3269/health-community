package project.healthcommunity.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberResult {
    private Long id;
    private String username;
    private int age;
    private int commentCount;
    @Builder
    @QueryProjection
    public MemberResult(Long id, String username, int age, int commentCount) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.commentCount = commentCount;
    }



}
