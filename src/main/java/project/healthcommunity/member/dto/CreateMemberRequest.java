package project.healthcommunity.member.dto;

import lombok.Data;

@Data
public class CreateMemberRequest {

    private String username;
    private int age;
}
