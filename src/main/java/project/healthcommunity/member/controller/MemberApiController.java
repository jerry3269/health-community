package project.healthcommunity.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.*;
import project.healthcommunity.member.repository.MemberRepository;
import project.healthcommunity.member.service.MemberService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/api/member")
    public MemberResponse saveMember(@RequestBody CreateMemberRequest request) {
        Member member = new Member(request.getUsername(), request.getAge());
        memberService.join(member);
        return new MemberResponse(member);
    }

    @GetMapping("/api/members")
    public List<MemberResponse> members() {
        return memberService.members().stream().map(MemberResponse::new).collect(toList());
    }

    @PutMapping("/api/member/{id}")
    public MemberResponse updateMember(
            @PathVariable("id") Long id,
            @RequestBody UpdateMemberDto request) {

        memberService.update(id, request.getUsername());
        Member findMember = memberService.findOne(id);
        return new MemberResponse(findMember);
    }

    @GetMapping("/api/member/{id}")
    public MemberResponse memberById(@PathVariable("id") Long id) {
        Member findMember = memberService.findOne(id);
        return new MemberResponse(findMember);
    }

    /**
     * @param condition
     * {
     *     "username": "",
     *     "ageGoe": ,
     *     "postCountGoe": ,
     *     "commentCountGoe":
     * }
     * @return
     */
    @GetMapping("/api/member/search")
    public Page<MemberResponse> searchMember(
            @RequestBody MemberSearchCond condition,
            @PageableDefault(page = 0, size = 10, sort = "postCount", direction = Sort.Direction.DESC) Pageable pageable) {
        return memberRepository.search(condition, pageable);
    }
}
