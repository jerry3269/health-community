package project.healthcommunity.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.member.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);
}
