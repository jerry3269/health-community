package project.healthcommunity.global.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.global.domain.StaticField;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.global.service.LoginService;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.trainer.domain.Trainer;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult
            bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Object loginUser = loginService.login(form.getLoginId(),
                form.getPassword());

        log.info("login? {}", loginUser);
        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        HttpSession session = request.getSession();

        if (loginUser instanceof Member) {
            Member loginMember = (Member) loginUser;
            //세션에 로그인 회원 정보 보관
            session.setAttribute(StaticField.LOGIN_MEMBER, loginMember);
        }

        if (loginUser instanceof Trainer) {
            Trainer loginTrainer = (Trainer) loginUser;
            //세션에 로그인 회원 정보 보관
            session.setAttribute(StaticField.LOGIN_TRAINER, loginTrainer);
        }

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        //세션을 삭제한다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
