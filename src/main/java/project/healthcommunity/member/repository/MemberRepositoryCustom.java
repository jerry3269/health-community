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
    Member save(Member member);
    Member getByLoginId(String loginId);
    Member getById(Long memberId);
    List<Member> findAll();
    Optional<List<Member>> findByUsername(String username);
    List<Member> getByUsername(String username);
    void deleteById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findById(Long memberId);




}
