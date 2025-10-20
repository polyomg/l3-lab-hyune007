package com.poly.lab8.interceptor;

import com.poly.lab8.entity.Account;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        session.setAttribute("securityUri", uri);
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/auth/login");
            return false;
        }
        if (uri.startsWith("/admin") && !Boolean.TRUE.equals(user.getAdmin())) {
            response.sendRedirect("/auth/login");
            return false;
        }
        return true;
    }
}
