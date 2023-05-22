package project.healthcommunity.global.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.dto.UpdateMemberDto;
import project.healthcommunity.util.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_MEMBER;
import static project.healthcommunity.global.error.ErrorStaticField.INVALID_PASSWORD;
import static project.healthcommunity.global.error.ErrorStaticField.NOT_MATCH;

@Slf4j
class MemberControllerTest extends ControllerTest {

    @Test
    @DisplayName("Member로그인 성공")
    void login_success() throws Exception {
        Member member = CreateAndSaveMember();
        LoginForm loginForm = loginMemberRequest(member);
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("아이디가 존재하지 않으면 로그인 실패")
    void login_404() throws Exception {
        Member member = CreateAndSaveMember();
        LoginForm loginForm = new LoginForm("hello", "DFdf");
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("비밀번호가 맞지 않으면 로그인 실패")
    void login405() throws Exception {
        Member member = CreateAndSaveMember();
        LoginForm loginForm = new LoginForm(TEST_ID, "not match");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is(NOT_MATCH));
    }

    @Test
    @DisplayName("LoginForm에 맞지 않으면 로그인 실패")
    void login_fail400() throws Exception {
        Member member = CreateAndSaveMember();
        LoginForm loginForm = new LoginForm(TEST_ID, "");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Member로그아웃")
    void logout() throws Exception {

        Member member = CreateAndSaveMember();
        MockHttpSession session = getMemberSession(member);

        mockMvc.perform(post("/member/logout")
                        .session(session))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 성공")
    void signup200() throws Exception {
        //when
        CreateMemberRequest createMemberRequest = CreateMemberRequest.builder()
                .username("testMember")
                .age(20)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .build();

        String content = objectMapper.writeValueAsString(createMemberRequest);

        mockMvc.perform(post("/member/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("회원가입시 중복된 아이디 실패")
    void signup() throws Exception {
        //when
        Member member = CreateAndSaveMember();
        CreateMemberRequest createMemberRequest = CreateMemberRequest.builder()
                .username("testMember")
                .age(20)
                .loginId(TEST_ID)
                .password(TEST_PASSWORD)
                .build();

        String content = objectMapper.writeValueAsString(createMemberRequest);

        //then
        mockMvc.perform(post("/member/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Member 업데이트 성공")
    void update200() throws Exception {
        Member member = CreateAndSaveMember();
        MockHttpSession memberSession = getMemberSession(member);

        UpdateMemberDto updateMemberDto = UpdateMemberDto.builder()
                .username("updateMember")
                .password("update_password")
                .build();

        String string = objectMapper.writeValueAsString(updateMemberDto);

        mockMvc.perform(patch("/member/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string)
                        .session(memberSession))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Member delete 성공")
    void delete200() throws Exception {
        Member member = CreateAndSaveMember();
        MockHttpSession memberSession = getMemberSession(member);

        mockMvc.perform(delete("/member/")
                        .session(memberSession))
                .andExpect(status().isOk());
    }
}