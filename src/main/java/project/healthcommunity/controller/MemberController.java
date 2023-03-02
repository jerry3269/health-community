package project.healthcommunity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.domain.Member;
import project.healthcommunity.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public String saveMember(@RequestBody Member member){
        memberService.save(member);
        return member.getUsername();
    }
}
