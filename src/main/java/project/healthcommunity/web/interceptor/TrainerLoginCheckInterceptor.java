package project.healthcommunity.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import project.healthcommunity.global.basic.BasicStaticField;


@Slf4j
public class TrainerLoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("Trainer 로그인 인증 인터 셉터 실행 [{}]", requestURI);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(BasicStaticField.LOGIN_TRAINER) == null) {
            log.info("미인증 Trainer 요청");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "미인증 Trainer 요청");
            return false;
        }
        log.info("Trainer 로그인 성공");
        return true;
    }
}
