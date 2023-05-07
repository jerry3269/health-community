package project.healthcommunity.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.*;
import project.healthcommunity.member.repository.MemberJpaRepository;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_MEMBER;
import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_TRAINER;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepositoryCustom memberRepositoryCustom;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginForm loginForm, HttpServletRequest request) {
        MemberResponse memberResponse = memberService.login(loginForm, request);
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = memberService.logout(request);
        return ResponseEntity.ok(session);
    }

    @PostMapping("/add")
    public Object saveMember(@RequestBody @Valid CreateMemberRequest request,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        Member member = new Member(request.getUsername(), request.getAge(), request.getLoginId(), request.getPassword());
        memberService.join(member);
        return new MemberResponse(member);
    }

    @GetMapping("/members")
    public List<MemberResponse> members() {
        return memberService.members().stream().map(MemberResponse::new).collect(toList());
    }

    @PutMapping("/{id}")
    public Object updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberDto request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        memberService.update(id, request.getUsername());
        Member findMember = memberService.findOne(id);
        return new MemberResponse(findMember);
    }

    @GetMapping("/{id}")
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
    @GetMapping("/search")
    public Object searchMember(
            @RequestBody @Valid MemberSearchCond condition,
            BindingResult bindingResult,
            @PageableDefault(page = 0, size = 10, sort = "postCount", direction = Sort.Direction.DESC) Pageable pageable) {

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }
        return memberRepositoryCustom.search(condition, pageable);
    }
}
