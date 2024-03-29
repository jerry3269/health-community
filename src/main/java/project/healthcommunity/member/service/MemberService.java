package project.healthcommunity.member.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.dto.MemberResponse;
import project.healthcommunity.member.domain.MemberSession;
import project.healthcommunity.member.dto.UpdateMemberDto;
import project.healthcommunity.member.exception.MemberDuplicationException;
import project.healthcommunity.member.exception.MemberPasswordNotMatchException;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepositoryCustom memberRepositoryCustom;
    private final TrainerRepositoryCustom trainerRepositoryCustom;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponse join(CreateMemberRequest createMemberRequest) {
        validDupLoginId(createMemberRequest.getLoginId());
        Member member = createMemberRequest.toMember(passwordEncoder);
        memberRepositoryCustom.save(member);
        return MemberResponse.createByMember(member);
    }

    private void validDupLoginId(String loginId) {
        Optional<Member> findMember = memberRepositoryCustom.findByLoginId(loginId);
        if (findMember.isPresent()) {
            throw new MemberDuplicationException();
        }

        Optional<Trainer> findTrainer = trainerRepositoryCustom.findByLoginId(loginId);
        if (findTrainer.isPresent()) {
            throw new MemberDuplicationException();
        }
    }

    @Transactional
    public MemberResponse login(LoginForm loginForm, HttpServletRequest request) {
        Member member = memberRepositoryCustom.getByLoginId(loginForm.getLoginId());
        if (passwordEncoder.matches(loginForm.getPassword(), member.getPassword())) {
            MemberSession memberSession = MemberSession.createMemberSession(member);
            HttpSession session = request.getSession();
            session.setAttribute(LOGIN_MEMBER, memberSession);
            return MemberResponse.createByMemberSession(memberSession);
        }
        throw new MemberPasswordNotMatchException();
    }

    @Transactional
    public HttpSession logout(MemberSession memberSession, HttpServletRequest request) {
        memberSession.invalidate(request);
        return request.getSession(false);
    }

    @Transactional
    public MemberResponse update(MemberSession memberSession, UpdateMemberDto updateMemberDto) {
        Member member = getById(memberSession.getId());
        updateMemberDto.encodingPassword(passwordEncoder);
        member.update(updateMemberDto);
        return MemberResponse.createByMember(member);

    }

    @Transactional
    public void upTrainerLikes(MemberSession memberSession, Long trainerId) {
        Trainer trainer = trainerRepositoryCustom.getById(trainerId);
        Member member = getById(memberSession.getId());
        trainer.upLikes();
    }

    @Transactional
    public void delete(MemberSession memberSession) {
        memberRepositoryCustom.deleteById(memberSession.getId());
    }


    public List<MemberResponse> findAll() {
        return memberRepositoryCustom.findAll().stream().map(m -> MemberResponse.createByMember(m)).collect(Collectors.toList());

    }

    public List<Member> getByUsername(String username) {
        return memberRepositoryCustom.getByUsername(username);
    }

    public List<Comment> getCommentByMember(Long memberId) {
        Member member = getById(memberId);
        return member.getCommentList();
    }

    public Member getById(Long memberId) {
        return memberRepositoryCustom.getById(memberId);
    }


}
