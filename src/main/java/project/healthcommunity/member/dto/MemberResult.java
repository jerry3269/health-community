package project.healthcommunity.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResult<T> {
    private T data;
    private int postCount;
    private int commentCount;
}
