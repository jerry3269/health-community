package project.healthcommunity.global.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.CreateTrainerRequest;
import project.healthcommunity.trainer.dto.UpdateTrainerRequest;
import project.healthcommunity.util.ControllerTest;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.healthcommunity.global.error.ErrorStaticField.NOT_MATCH;


class TrainerControllerTest extends ControllerTest {


    @Test
    @DisplayName("Trainer로그인 성공 200")
    void login200() throws Exception {
        Trainer trainer = createAndSaveTestTrainer();
        LoginForm loginForm = loginTrainerRequest(trainer);
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(document("trainer-login/200"));
    }


    @Test
    @DisplayName("아이디가 존재하지 않으면 로그인 실패 404")
    void login404() throws Exception {
        Trainer trainer = createAndSaveTestTrainer();
        LoginForm loginForm = new LoginForm("fail", "fail");
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNotFound())
                .andDo(document("trainer-login/404"));
    }

    @Test
    @DisplayName("비밀번호가 맞지 않으면 로그인 실패 405")
    void login405() throws Exception {
        Trainer trainer = createAndSaveTestTrainer();
        LoginForm loginForm = new LoginForm(TEST_ID, "not match");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is(NOT_MATCH))
                .andDo(document("trainer-login/405"));
    }

    @Test
    @DisplayName("LoginForm에 맞지 않으면 로그인 실패 400")
    void login400() throws Exception {
        Trainer trainer = createAndSaveTestTrainer();
        LoginForm loginForm = new LoginForm(TEST_ID, "");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andDo(document("trainer-login/400"));
    }

    @Test
    @DisplayName("trainer 로그아웃 성공 200")
    void logout200() throws Exception {

        Trainer trainer = createAndSaveTestTrainer();
        MockHttpSession session = getTrainerSession(trainer);

        mockMvc.perform(post("/trainer/logout")
                        .session(session))
                .andExpect(status().isOk())
                .andDo(document("trainer-logout/200"));
    }

    @Test
    @DisplayName("trainer 회원가입 성공 200")
    void signup200() throws Exception {
        //when
        CreateTrainerRequest createTrainerRequest = CreateTrainerRequest.builder()
                .trainerName("testTrainer")
                .age(20)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .career(20)
                .build();

        String content = objectMapper.writeValueAsString(createTrainerRequest);

        mockMvc.perform(post("/trainer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(document("trainer-signup/200"));

    }

    @Test
    @DisplayName("trainer 회원가입시 중복된 아이디 실패 400")
    void signup400() throws Exception {
        //when
        Trainer trainer = createAndSaveTestTrainer();
        CreateTrainerRequest createTrainerRequest = CreateTrainerRequest.builder()
                .trainerName("testMember")
                .age(20)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .career(20)
                .build();

        String content = objectMapper.writeValueAsString(createTrainerRequest);

        //then
        mockMvc.perform(post("/trainer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andDo(document("trainer-signup/400"));
    }

    @Test
    @DisplayName("Trainer 업데이트 성공 200")
    void update200() throws Exception {
        Trainer trainer = createAndSaveTestTrainer();
        MockHttpSession session = getTrainerSession(trainer);

        UpdateTrainerRequest updateTrainerRequest = UpdateTrainerRequest.builder()
                .trainerName("updateTrainer")
                .password("update_password")
                .build();

        String string = objectMapper.writeValueAsString(updateTrainerRequest);

        mockMvc.perform(patch("/trainer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string)
                        .session(session))
                .andExpect(status().isOk())
                .andDo(document("trainer-update/200"));
    }

    @Test
    @DisplayName("Trainer 탈퇴후 update시도 시 실패 404")
    void update404() throws Exception {
        Trainer trainer = createAndSaveTestTrainer();
        MockHttpSession session = getTrainerSession(trainer);
        deleteSession((session));

        UpdateTrainerRequest updateTrainerRequest = UpdateTrainerRequest.builder()
                .trainerName("updateTrainer")
                .password("update_password")
                .build();

        String string = objectMapper.writeValueAsString(updateTrainerRequest);

        mockMvc.perform(patch("/trainer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string)
                        .session(session))
                .andExpect(status().isNotFound())
                .andDo(document("trainer-update/404"));
    }

    @Test
    @DisplayName("Trainer delete 성공")
    void delete200() throws Exception {

        Trainer trainer = createAndSaveTestTrainer();
        MockHttpSession session = getTrainerSession(trainer);

        int status = deleteSession(session);
        assertThat(status).isEqualTo(200);
    }

    @Test
    @DisplayName("경력 10년이상, 좋아요 1개 이상 Trainer Search성공 200")
    void search200() throws Exception {

        mockMvc.perform(get("/trainer/search")
                        .queryParam("careerGoe", "10")
                        .queryParam("likesGoe", "1")
                        .queryParam("size", "20"))
                .andExpect(status().isOk())
                .andDo(document("trainer-search/200"));
    }
}