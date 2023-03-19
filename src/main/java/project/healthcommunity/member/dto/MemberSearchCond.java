package project.healthcommunity.member.dto;

import lombok.Data;

@Data
public class MemberSearchCond {
    private String username;
    private Integer ageGoe;
    private Integer commentCountGoe;
}
