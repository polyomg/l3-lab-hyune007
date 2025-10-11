package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParamController {
    @GetMapping("/param/form")
    public String form() {
        return "form";
    }

    //    @PostMapping("/param/save/{x}")
//    public String save(@PathVariable String x, @RequestParam String y, Model model) {
//        model.addAttribute("x", x);
//        model.addAttribute("y", y);
//        return "form";
//    }
    @RequestMapping("/param/save/{x}")
    public String save(@PathVariable String x, @RequestParam String y, Model model) {
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        return "form";
    }
}

