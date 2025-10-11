package com.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RectangleController {
    @Autowired
    HttpServletRequest request;

    @GetMapping("/rectangle/form")
    public String form(){
        return "rectangle";
    }

    @PostMapping("/rectangle/calculator")
    public String calculator(Model model){
        try {
            double length = Double.parseDouble(request.getParameter("length"));
            double width = Double.parseDouble(request.getParameter("width"));

            if (length <= width) {
                double temp = length;
                length = width;
                width = temp;
            }
            double areaR = length * width;
            double paraR = (length + width) * 2;
            model.addAttribute("area", length + "*" + width + "=" + areaR);
            model.addAttribute("perimeter", "(" + length + "+" + width + ")" + "*2=" + paraR);
        } catch (Exception e){
            model.addAttribute ("area", null);
            model.addAttribute ("perimeter", null);
        }
        return "rectangle";
    }
}
