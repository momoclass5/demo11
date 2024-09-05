package com.example.demo11;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 스프링 설정을 등록
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor1()) // 실행할 인터셉터
                .addPathPatterns("/book/*", "/member/*") // 적용할 URL패턴

                // 로그인 페이지가 제외할 패턴으로 등록되어 있지 않은경우
                // 로그인페이지로 계속 리다이렉트 요청이 일어난다
                .excludePathPatterns("/book/list", "/member/login", "/member/register"); // 제외할 패턴

        // TODO Auto-generated method stub
        // WebMvcConfigurer.super.addInterceptors(registry);
    }
}
