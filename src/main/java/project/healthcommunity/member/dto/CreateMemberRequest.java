package project.healthcommunity.member.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMemberRequest {
    @NotBlank
    private String username;
    @NotNull
    @Min(0)
    private int age;
}
