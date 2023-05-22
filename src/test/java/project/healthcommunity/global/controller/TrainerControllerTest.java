package project.healthcommunity.global.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.dto.UpdateMemberDto;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.CreateTrainerRequest;
import project.healthcommunity.trainer.dto.UpdateTrainerRequest;
import project.healthcommunity.util.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_TRAINER;
import static project.healthcommunity.global.error.ErrorStaticField.NOT_MATCH;

@Slf4j
class TrainerControllerTest extends ControllerTest {

    @Test
    @DisplayName("Trainer로그인 성공")
    void login_success() throws Exception {
        Trainer trainer = CreateAndSaveTrainer();
        LoginForm loginForm = loginTrainerRequest(trainer);
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("아이디가 존재하지 않으면 로그인 실패")
    void login_fail() throws Exception {
        Trainer trainer = CreateAndSaveTrainer();
        LoginForm loginForm = new LoginForm("fail", "fail");
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("비밀번호가 맞지 않으면 로그인 실패")
    void login405() throws Exception {
        Trainer trainer = CreateAndSaveTrainer();
        LoginForm loginForm = new LoginForm(TEST_ID, "not match");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is(NOT_MATCH));
    }

    @Test
    @DisplayName("LoginForm에 맞지 않으면 로그인 실패")
    void login_fail400() throws Exception {
        Trainer trainer = CreateAndSaveTrainer();
        LoginForm loginForm = new LoginForm(TEST_ID, "");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/trainer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("trainer 로그아웃 성공")
    void logout200() throws Exception {

        Trainer trainer = CreateAndSaveTrainer();
        MockHttpSession session = getTrainerSession(trainer);

        mockMvc.perform(post("/trainer/logout")
                        .session(session))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("trainer 회원가입 성공")
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
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("trainer 회원가입시 중복된 아이디 실패")
    void signup() throws Exception {
        //when
        Trainer trainer = CreateAndSaveTrainer();
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
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Trainer 업데이트 성공")
    void update200() throws Exception {
        Trainer trainer = CreateAndSaveTrainer();
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
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Trainer delete 성공")
    void delete200() throws Exception {
        Trainer trainer = CreateAndSaveTrainer();
        MockHttpSession session = getTrainerSession(trainer);

        mockMvc.perform(delete("/trainer/")
                        .session(session))
                .andExpect(status().isOk());
    }
}