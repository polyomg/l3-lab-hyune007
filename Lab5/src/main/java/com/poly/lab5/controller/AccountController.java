package com.poly.lab5.controller;

import com.poly.lab5.service.CookieService;
import com.poly.lab5.service.ParamService;
import com.poly.lab5.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2(Model model) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);
        if (un.equals("poly") && pw.equals("123")) {
            sessionService.set("username", un);
            model.addAttribute("message", un + " đăng nhập thành công!");
        } else {
            sessionService.remove("username");
        }
        if (rm) {
            cookieService.add("user", un, 10);
        } else {
            cookieService.remove("user");
        }
        return "/account/login";
    }

    @GetMapping("/account/register")
    public String showRegisterForm() {
        return "account/register";
    }

    @PostMapping("/account/register")
    public String register(Model model,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("photo") MultipartFile photo) {

        File savedFile = paramService.save(photo, "/uploads/");

        model.addAttribute("username", username);
        model.addAttribute("password", password);

        if (savedFile != null) {
            model.addAttribute("photoName", savedFile.getName());
        } else {
            model.addAttribute("photoName", null);
        }                                                                                                                               //huynt

        return "account/register-success";
    }
}
