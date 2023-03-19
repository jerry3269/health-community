package project.healthcommunity.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberResult<T> {
    private T data;
    private int commentCount;

    @Builder
    public MemberResult(T data, int commentCount) {
        this.data = data;
        this.commentCount = commentCount;
    }
}
