package project.healthcommunity.member.dto;

import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSearchCond {
    private String username;
    @Min(0)
    private Integer ageGoe;
    @Min(0)
    private Integer commentCountGoe;
}
