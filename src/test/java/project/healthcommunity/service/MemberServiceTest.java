package project.healthcommunity.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.service.MemberService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @PersistenceContext
    EntityManager em;

    @Test
    void join() {
        Member member1 = new Member("m1", 10);
        memberService.join(member1);


        Member findM1 = memberService.findOne(member1.getId());

        assertThat(findM1).isEqualTo(member1);
    }

    @Test
    void validDupMember() {
        Member member1 = new Member("m1", 10);
        Member member2 = new Member("m1", 20);
        memberService.join(member1);

        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

    }

    @Test
    void update() {
        Member member1 = new Member("m1", 10);
        memberService.join(member1);


        memberService.update(member1.getId(), "m1");

        em.flush();
        em.clear();

        Member findM1 = memberService.findOne(member1.getId());

        assertThat(findM1.getAge()).isEqualTo(20);
    }


    @Test
    void members() {
        Member member1 = new Member("m1", 10);
        Member member2 = new Member("m2", 20);
        Member member3 = new Member("m3", 30);
        Member member4 = new Member("m4", 40);
        Member member5 = new Member("m5", 50);
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
        memberService.join(member4);
        memberService.join(member5);

        List<Member> members = memberService.members();
        for (Member member : members) {
            System.out.println("member = " + member);
        }

    }
}