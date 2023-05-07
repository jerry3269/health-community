package project.healthcommunity.member.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.MemberResponse;
import project.healthcommunity.member.dto.MemberSearchCond;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Page<MemberResponse> search(MemberSearchCond condition, Pageable pageable);
    void save(Member member);
    List<Member> findByLoginId(String loginId);
    Member findById(Long id);
    List<Member> findAll();
    List<Member> findByUsername(String username);
    void deleteById(Long id);
    Member getByLoginId(String loginId);



}
