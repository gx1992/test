package com.cn.uk.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MobileInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(WebConfigurer.SESSION_KEY) != null)
            return true;

        // 跳转登录
        String url = request.getContextPath() + "/m/login";
        response.sendRedirect(url);
        return false;
    }
}