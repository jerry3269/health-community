package project.healthcommunity.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
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
import project.healthcommunity.member.repository.MemberJpaRepository;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.dto.CreatePostRequest;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.repository.TrainerJpaRepository;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
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
    protected String TEST_TITLE = "test_title";
    protected String TEST_CONTENT = " test_content";
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

    protected Member createAndSaveTestMember() {
        Member testMember = Member.builder()
                .username("testMember")
                .age(20)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .build();

        return memberRepositoryCustom.save(testMember);
    }

    protected Trainer createAndSaveTestTrainer() {
        Trainer testTrainer = Trainer.noCertificateBuilder()
                .trainerName("testTrainer")
                .age(20)
                .career(10)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .build();

        return trainerRepositoryCustom.save(testTrainer);
    }

    protected CreatePostRequest createTestPostRequest(Long trainerId){
        return CreatePostRequest.builder()
                .trainerId(trainerId)
                .title(TEST_TITLE)
                .content(TEST_CONTENT)
                .categoryNameList(List.of("팔굽"))
                .build();
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

    protected int deleteSession(MockHttpSession session) throws Exception {
        return mockMvc.perform(delete("/trainer/")
                .session(session))
                .andReturn()
                .getResponse()
                .getStatus();
    }
}
