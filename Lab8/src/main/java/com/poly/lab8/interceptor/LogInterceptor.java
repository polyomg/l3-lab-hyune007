package com.poly.lab8.interceptor;

import com.poly.lab8.entity.Account;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user != null) {
            System.out.println(request.getRequestURI() + ", " + new Date() + ", " + user.getFullname());
        }
        return true;
    }
}
