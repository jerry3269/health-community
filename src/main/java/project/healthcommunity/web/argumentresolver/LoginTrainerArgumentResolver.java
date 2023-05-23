package project.healthcommunity.web.argumentresolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import project.healthcommunity.global.controller.LoginForMember;
import project.healthcommunity.global.controller.LoginForTrainer;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.domain.TrainerSession;
import project.healthcommunity.trainer.exception.TrainerUnauthorizedException;

import static project.healthcommunity.global.basic.BasicStaticField.*;

@Slf4j
public class LoginTrainerArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("LoginTrainerArgumentResolver supportsParameter 실행");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(LoginForTrainer.class);
        boolean hasMemberType = TrainerSession.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("LoginTrainerArgumentResolver resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(LOGIN_TRAINER) == null) {
            throw new TrainerUnauthorizedException();
        }
        return session.getAttribute(LOGIN_TRAINER);
    }
}
