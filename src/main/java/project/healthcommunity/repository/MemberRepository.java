package project.healthcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
