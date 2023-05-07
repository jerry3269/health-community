package project.healthcommunity.member.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.MemberResponse;
import project.healthcommunity.member.exception.MemberDuplicationLoginIdException;
import project.healthcommunity.member.exception.MemberNotFoundException;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.repository.TrainerJpaRepository;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;

import java.util.List;

import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepositoryCustom memberRepositoryCustom;
    private final TrainerRepositoryCustom trainerRepositoryCustom;

    @Transactional
    public void join(Member member){
        validDupLoginId(member.getLoginId());
        memberRepositoryCustom.save(member);
    }
    private void validDupLoginId(String loginId){
        List<Member> findMember = memberRepositoryCustom.findByLoginId(loginId);
        if (!findMember.isEmpty()) {
            throw new MemberDuplicationLoginIdException();
        }

        List<Trainer> findTrainer = trainerRepositoryCustom.findByLoginId(loginId);
        if(!findTrainer.isEmpty()) {
            throw new MemberDuplicationLoginIdException();
        }
    }

    public MemberResponse login(LoginForm loginForm, HttpServletRequest request) {
        Member member = memberRepositoryCustom.getByLoginId(loginForm.getLoginId());
        if (member.getPassword().equals(loginForm.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute(LOGIN_MEMBER, member);
            return new MemberResponse(member);
        }
        throw new MemberNotFoundException();
    }

    public HttpSession logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return session;
    }

    @Transactional
    public void update(Long id, String username){
        Member findMember = findOne(id);
        findMember.update(username);
    }

    @Transactional
    public void upTrainerLikes(Long memberId, Long trainerId) {
        Trainer trainer = trainerRepositoryCustom.findOne(trainerId);
        Member member = findOne(memberId);
        trainer.upLikes();
    }

    public Member findOne(Long id){
        return memberRepositoryCustom.findById(id);
    }

    /**
     * 전체 조회
     */
    public List<Member> members(){
        return memberRepositoryCustom.findAll();
    }

    public List<Member> findByUsername(String username) {
        return memberRepositoryCustom.findByUsername(username);
    }

    public List<Comment> findCommentByMember(Long id) {
        Member member = findOne(id);
        return member.getCommentList();
    }

    @Transactional
    public void delete(Member member) {
        Long id = member.getId();
        memberRepositoryCustom.deleteById(id);
    }



}
