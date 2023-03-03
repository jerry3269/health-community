package project.healthcommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.domain.Member;
import project.healthcommunity.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원가입
     */
    @Transactional
    public void join(Member member){
        validDupMember(member);
        memberRepository.save(member);
    }

    /**
     * 중복회원 검사
     */
    public void validDupMember(Member member){
        List<Member> findMember = memberRepository.findByUsername(member.getUsername());
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 수정
     */
    @Transactional
    public void update(Long id, String username, int age){
        Member findMember = findOne(id);
        findMember.update(username, age);
    }

    /**
     * 아이디로 조회
     */
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





}
