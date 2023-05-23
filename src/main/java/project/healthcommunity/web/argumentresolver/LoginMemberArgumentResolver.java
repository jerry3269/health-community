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
import project.healthcommunity.member.domain.MemberSession;
import project.healthcommunity.member.exception.MemberUnauthorizedException;

import static project.healthcommunity.global.basic.BasicStaticField.*;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("LoginMemberArgumentResolver supportsParameter 실행");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(LoginForMember.class);
        boolean hasMemberType = MemberSession.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("LoginMemberArgumentResolver resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
            throw new MemberUnauthorizedException();
        }
        return session.getAttribute(LOGIN_MEMBER);
    }
}
