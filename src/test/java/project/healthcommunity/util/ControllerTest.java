package project.healthcommunity.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.repository.MemberJpaRepository;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.CreateTrainerRequest;
import project.healthcommunity.trainer.repository.TrainerJpaRepository;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;
import project.healthcommunity.trainer.service.TrainerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_MEMBER;
import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_TRAINER;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MemberService memberService;
    @Autowired
    protected TrainerService trainerService;
    @Autowired
    protected MemberRepositoryCustom memberRepositoryCustom;
    @Autowired
    protected TrainerRepositoryCustom trainerRepositoryCustom;

    @Autowired
    protected MemberJpaRepository memberJpaRepository;
    @Autowired
    protected TrainerJpaRepository trainerJpaRepository;

    protected String TEST_ID = "test_id";
    protected String TEST_PASSWORD = "test_password";

    @BeforeEach
    void initial(){
        memberJpaRepository.deleteAll();
        trainerJpaRepository.deleteAll();
    }

    protected LoginForm loginMemberRequest(Member member) {

        return LoginForm.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .build();
    }

    protected LoginForm loginTrainerRequest(Trainer trainer){
        return LoginForm.builder()
                .loginId(trainer.getLoginId())
                .password(trainer.getPassword())
                .build();
    }

    protected Member CreateAndSaveMember() {
        Member testMember = Member.builder()
                .username("testMember")
                .age(20)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .build();

        return memberRepositoryCustom.save(testMember);
    }

    protected Trainer CreateAndSaveTrainer() {
        Trainer testTrainer = Trainer.noCertificateBuilder()
                .trainerName("testTrainer")
                .age(20)
                .career(10)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .build();

        return trainerRepositoryCustom.save(testTrainer);
    }

    protected MockHttpSession getMemberSession(Member member) throws Exception {
        LoginForm loginForm = loginMemberRequest(member);

        String content = objectMapper.writeValueAsString(loginForm);

        MockHttpServletRequest request = mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                        .andReturn().getRequest();

        HttpSession session = request.getSession();
        return (MockHttpSession)session;
    }

    protected MockHttpSession getTrainerSession(Trainer trainer) throws Exception {
        LoginForm loginForm = loginTrainerRequest(trainer);

        String content = objectMapper.writeValueAsString(loginForm);

        MockHttpServletRequest request = mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andReturn().getRequest();

        HttpSession session = request.getSession();
        return (MockHttpSession)session;
    }
}
