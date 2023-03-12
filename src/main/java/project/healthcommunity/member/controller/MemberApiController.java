package project.healthcommunity.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.dto.CreateMemberResponse;
import project.healthcommunity.member.dto.MemberDto;
import project.healthcommunity.member.dto.UpdateMemberDto;
import project.healthcommunity.member.service.MemberService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/member")
    public CreateMemberResponse saveMemberV1(@RequestBody CreateMemberRequest request) {
        Member member = new Member(request.getUsername(), request.getAge());
        memberService.join(member);
        return new CreateMemberResponse(member);
    }

    @GetMapping("/api/members")
    public List<MemberDto> members() {
        return memberService.members().stream().map(MemberDto::new).collect(toList());
    }

    @PutMapping("/api/member/{id}")
    public MemberDto updateMember(
            @PathVariable("id") Long id,
            @RequestBody UpdateMemberDto request) {

        memberService.update(id, request.getUsername());
        Member findMember = memberService.findOne(id);
        return new MemberDto(findMember);
    }

}
