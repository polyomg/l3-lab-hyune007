package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OkController {
    @RequestMapping("/ok")
    public String ok() {
        return "ok";
    }

    @PostMapping(value = "/ctrl/ok")
    public String m1(Model model) {
        model.addAttribute("methodName", "m1");
        return "ok";
    }

    @GetMapping(value = "/ctrl/ok")
    public String m2(Model model) {
        model.addAttribute("methodName", "m2");
        return "ok";
    }

    @PostMapping(value = "/ctrl/ok", params = "x")
    public String m3(Model model) {
        model.addAttribute("methodName", "m3");
        return "ok";
    }
}
