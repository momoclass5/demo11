package com.example.demo11;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterCeptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info("LoginInterCeptor =================");
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null || session.getAttribute("userId") == "") {
            // 로그인이 되어 있지 않으면 로그인 페이지로 이동
            request.setAttribute("msg", "로그인후 사용이 가능 합니다.");
            response.sendRedirect("/login");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
