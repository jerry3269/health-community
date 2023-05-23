package project.healthcommunity.member.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.dto.MemberResponse;
import project.healthcommunity.member.domain.MemberSession;
import project.healthcommunity.member.dto.UpdateMemberDto;
import project.healthcommunity.member.exception.MemberDuplicationLoginIdException;
import project.healthcommunity.member.exception.MemberNotMatchException;
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

    @Transactional
    public MemberResponse join(CreateMemberRequest createMemberRequest) {
        validDupLoginId(createMemberRequest.getLoginId());
        Member member = CreateMemberRequest.toMember(createMemberRequest);
        memberRepositoryCustom.save(member);
        return MemberResponse.createByMember(member);
    }

    private void validDupLoginId(String loginId) {
        Optional<Member> findMember = memberRepositoryCustom.findByLoginId(loginId);
        if (findMember.isPresent()) {
            throw new MemberDuplicationLoginIdException();
        }

        Optional<Trainer> findTrainer = trainerRepositoryCustom.findByLoginId(loginId);
        if (findTrainer.isPresent()) {
            throw new MemberDuplicationLoginIdException();
        }
    }

    @Transactional
    public MemberResponse login(LoginForm loginForm, HttpServletRequest request) {
        Member member = memberRepositoryCustom.getByLoginId(loginForm.getLoginId());
        if (member.getPassword().equals(loginForm.getPassword())) {
            MemberSession memberSession = MemberSession.createMemberSession(member);
            HttpSession session = request.getSession();
            session.setAttribute(LOGIN_MEMBER, memberSession);
            return MemberResponse.createByMemberSession(memberSession);
        }
        throw new MemberNotMatchException();
    }

    @Transactional
    public HttpSession logout(MemberSession memberSession, HttpServletRequest request) {
        memberSession.invalidate(request);
        return request.getSession(false);
    }

    @Transactional
    public MemberResponse update(MemberSession memberSession, UpdateMemberDto updateMemberDto) {
        Member member = findOne(memberSession.getId());
        member.update(updateMemberDto);
        return MemberResponse.createByMember(member);

    }

    @Transactional
    public void upTrainerLikes(MemberSession memberSession, Long trainerId) {
        Trainer trainer = trainerRepositoryCustom.getById(trainerId);
        Member member = findOne(memberSession.getId());
        trainer.upLikes();
    }

    @Transactional
    public void delete(MemberSession memberSession) {
        memberRepositoryCustom.deleteById(memberSession.getId());
    }


    public List<MemberResponse> members() {
        return memberRepositoryCustom.findAll().stream().map(m -> MemberResponse.createByMember(m)).collect(Collectors.toList());

    }

    public List<Member> findByUsername(String username) {
        return memberRepositoryCustom.findByUsername(username);
    }

    public List<Comment> findCommentByMember(Long id) {
        Member member = findOne(id);
        return member.getCommentList();
    }

    public Member findOne(Long id) {
        return memberRepositoryCustom.getById(id);
    }


}
