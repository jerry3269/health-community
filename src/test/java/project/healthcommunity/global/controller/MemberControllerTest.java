package project.healthcommunity.global.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import project.healthcommunity.certificate.controller.CertificateController;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.controller.MemberController;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.dto.UpdateMemberDto;
import project.healthcommunity.util.ControllerTest;

import java.lang.annotation.Documented;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.healthcommunity.global.error.ErrorStaticField.NOT_MATCH;

class MemberControllerTest extends ControllerTest {

    @Test
    @DisplayName("Member로그인 성공 200")
    void login_success() throws Exception {
        Member member = createAndSaveTestMember();
        LoginForm loginForm = loginMemberRequest(member);
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(document("member-login/200"));
    }


    @Test
    @DisplayName("아이디가 존재하지 않으면 로그인 실패 404")
    void login_404() throws Exception {
        Member member = createAndSaveTestMember();
        LoginForm loginForm = new LoginForm("hello", "DFdf");
        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNotFound())
                .andDo(document("member-login/404"));

    }

    @Test
    @DisplayName("비밀번호가 맞지 않으면 로그인 실패 405")
    void login405() throws Exception {
        Member member = createAndSaveTestMember();
        LoginForm loginForm = new LoginForm(TEST_ID, "not match");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is(NOT_MATCH))
                .andDo(document("member-login/405"));
    }

    @Test
    @DisplayName("LoginForm에 맞지 않으면 로그인 실패 400")
    void login_fail400() throws Exception {
        Member member = createAndSaveTestMember();
        LoginForm loginForm = new LoginForm(TEST_ID, "");

        String content = objectMapper.writeValueAsString(loginForm);

        //when
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andDo(document("member-login/400"));
    }

    @Test
    @DisplayName("Member로그아웃 200")
    void logout200() throws Exception {

        Member member = createAndSaveTestMember();
        MockHttpSession session = getMemberSession(member);

        mockMvc.perform(post("/member/logout")
                        .session(session))
                .andExpect(status().isOk())
                .andDo(document("member-logout/200"));
    }

    @Test
    @DisplayName("회원가입 성공 200")
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
                .andExpect(status().isOk())
                .andDo(document("member-signup/200"));
    }

    @Test
    @DisplayName("회원가입시 중복된 아이디 실패 400")
    void signup400() throws Exception {
        //when
        Member member = createAndSaveTestMember();
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
                .andExpect(status().isBadRequest())
                .andDo(document("member-signup/400"));
    }

    @Test
    @DisplayName("Member 업데이트 성공 200")
    void update200() throws Exception {
        Member member = createAndSaveTestMember();
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
                .andExpect(status().isOk())
                .andDo(document("member-update/200"));
    }

    @Test
    @DisplayName("Member delete 성공 200")
    void delete200() throws Exception {
        Member member = createAndSaveTestMember();
        MockHttpSession memberSession = getMemberSession(member);

        mockMvc.perform(delete("/member/")
                        .session(memberSession))
                .andExpect(status().isOk())
                .andDo(document("member-delete/200"));
    }
}