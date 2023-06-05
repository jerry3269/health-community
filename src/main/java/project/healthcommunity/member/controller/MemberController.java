package project.healthcommunity.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.global.controller.LoginForMember;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.global.exception.BindingException;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.domain.MemberSession;
import project.healthcommunity.member.dto.*;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.member.service.MemberService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepositoryCustom memberRepositoryCustom;

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody @Valid LoginForm loginForm, HttpServletRequest request) {
        MemberResponse memberResponse = memberService.login(loginForm, request);
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@LoginForMember MemberSession memberSession, HttpServletRequest request) {
        HttpSession session = memberService.logout(memberSession, request);
        return ResponseEntity.ok(session);
    }

    @PostMapping("/add")
    public ResponseEntity<MemberResponse> saveMember(@RequestBody @Valid CreateMemberRequest createMemberRequest) {
        MemberResponse memberResponse = memberService.join(createMemberRequest);
        return ResponseEntity.ok(memberResponse);
    }

    @PatchMapping("/")
    public ResponseEntity<MemberResponse> update(
            @LoginForMember MemberSession memberSession,
            @RequestBody @Valid UpdateMemberDto updateMemberDto
    ) {

        MemberResponse memberResponse = memberService.update(memberSession, updateMemberDto);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/")
    public ResponseEntity<MemberResponse> memberById(@LoginForMember MemberSession memberSession) {
        Member findMember = memberService.getById(memberSession.getId());
        return ResponseEntity.ok(MemberResponse.createByMember(findMember));
    }

    @DeleteMapping("/")
    public ResponseEntity delete(@LoginForMember MemberSession memberSession) {
        memberService.delete(memberSession);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MemberResponse>> searchMember(
            @ModelAttribute @Valid MemberSearchCond condition,
            BindingResult bindingResult,
            @PageableDefault(page = 0, size = 10, sort = "postCount", direction = Sort.Direction.DESC) Pageable pageable) {

        BindingException.validate(bindingResult);
        Page<MemberResponse> memberResponsePage = memberRepositoryCustom.search(condition, pageable);
        return ResponseEntity.ok(memberResponsePage);
    }
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> members() {
        List<MemberResponse> memberResponseList = memberService.findAll();
        return ResponseEntity.ok(memberResponseList);
    }
}
