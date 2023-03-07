package project.healthcommunity.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.member.domain.Member;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void basicTest(){
        Member member = new Member("m1", 10);
        em.persist(member);

        Member findMember = em.find(Member.class, member.getId());

        assertThat(findMember).isEqualTo(member);
    }

}