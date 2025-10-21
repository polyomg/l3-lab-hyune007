package com.poly.lab8.controller;

import com.poly.lab8.entity.Account;
import com.poly.lab8.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        Account user = accountService.findById(username).orElse(null);
        if (user == null) {
            model.addAttribute("message", "Invalid username!");
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
        } else {
            session.setAttribute("user", user);
            model.addAttribute("message", "Login successfully!");
            String securityUri = (String) session.getAttribute("securityUri");
            if (securityUri != null) {
                return "redirect:" + securityUri;
            }
        }
        return "auth/login";
    }
}
