package com.poly.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {
    @RequestMapping("/home/index")
    public String index(Model model) {
        return "home";
    }

    @RequestMapping("/home/about")
    public String about(Model model) {
        return "about";
    }
}
