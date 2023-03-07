package project.healthcommunity.member.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.dto.CreateMemberResponse;
import project.healthcommunity.member.dto.MemberDto;
import project.healthcommunity.member.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/save/member")
    public CreateMemberResponse saveMemberV1(@RequestBody CreateMemberRequest request) {
        Member member = new Member(request.getUsername(), request.getAge());
        memberService.join(member);
        return new CreateMemberResponse(member);
    }

    @GetMapping("/api/members")
    public List<MemberDto> members(){
        return memberService.members().stream().map(MemberDto::new).collect(toList());
    }


}
