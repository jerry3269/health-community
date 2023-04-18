package project.healthcommunity.member.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MemberSearchCond {
    private String username;
    @Min(0)
    private Integer ageGoe;
    @Min(0)
    private Integer commentCountGoe;
}
