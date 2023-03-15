package project.healthcommunity.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.repository.MemberRepository;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.repository.TrainerRepository;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final TrainerService trainerService;



    @Transactional
    public void join(Member member){
        validDupMember(member);
        memberRepository.save(member);
    }


    public void validDupMember(Member member){
        List<Member> findMember = memberRepository.findByUsername(member.getUsername());
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional
    public void update(Long id, String username){
        Member findMember = findOne(id);
        findMember.update(username);
    }

    public Member findOne(Long id){
        Optional<Member> findMember = memberRepository.findById(id);
        if(!findMember.isPresent()){
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return findMember.get();
    }

    /**
     * 전체 조회
     */
    public List<Member> members(){
        return memberRepository.findAll();
    }

    public List<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public List<Comment> findCommentByMember(Long id) {
        Member member = findOne(id);
        return member.getCommentList();
    }

    @Transactional
    public void upTrainerLikes(Long memberId, Long trainerId) {
        Trainer trainer = trainerService.findOne(trainerId);
        Member member = findOne(memberId);
        trainer.upLikes();
    }

}
