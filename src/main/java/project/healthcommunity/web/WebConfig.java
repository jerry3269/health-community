package project.healthcommunity.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.healthcommunity.web.argumentresolver.LoginMemberArgumentResolver;
import project.healthcommunity.web.argumentresolver.LoginTrainerArgumentResolver;
import project.healthcommunity.web.interceptor.LogInterceptor;
import project.healthcommunity.web.interceptor.MemberLoginCheckInterceptor;
import project.healthcommunity.web.interceptor.TrainerLoginCheckInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new MemberLoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/member/login","/member/logout","/","/trainer/**", "/css/**", "/*.ico", "/error");

        registry.addInterceptor(new TrainerLoginCheckInterceptor())
                .order(3)
                .addPathPatterns("/**")
                .excludePathPatterns("/trainer/login","/trainer/logout","/","/member/**", "/css/**", "/*.ico", "/error");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
        resolvers.add(new LoginTrainerArgumentResolver());
    }
}
