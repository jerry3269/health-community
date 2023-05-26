package project.healthcommunity.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findById(Long memberId);
}
