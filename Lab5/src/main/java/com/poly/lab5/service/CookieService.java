package com.poly.lab5.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;

    public Cookie get(String name) {
        Cookie[] cookies = req.getCookies ();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName ().equals (name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public String getValue(String name) {
        Cookie cookie = get (name);
        return (cookie != null) ? cookie.getValue () : "";
    }

    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie (name, value);
        cookie.setPath ("/");
        cookie.setMaxAge (3600 * hours);
        resp.addCookie (cookie);
        return cookie;
    }

    public void remove(String name) {
        Cookie cookie = new Cookie (name, null);
        cookie.setMaxAge (0);
        cookie.setPath ("/");
        resp.addCookie (cookie);
    }
}
