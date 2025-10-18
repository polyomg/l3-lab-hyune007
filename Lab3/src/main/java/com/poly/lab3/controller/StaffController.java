package com.poly.lab3.controller;

import com.poly.lab3.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Arrays;

@Controller
public class StaffController {
    @GetMapping("/staff/detail")
    public String detail(Model model){
        Staff staff = Staff.builder().id ("TS00689").fullname ("nguyễn trường huy").level (1).build ();
        model.addAttribute ("staff",staff);
        return "staff-detail";
    }                                                                                                                       //huynt
    @GetMapping("/staff/list")
    public String list(Model model){
        List<Staff> staffList = Arrays.asList(
                Staff.builder().id("TS00689").fullname("Nguyễn Trường Huy").level(1).build(),
                Staff.builder().id("TS00690").fullname("Nguyễn Trường Huy 2").level(2).build(),
                Staff.builder().id("TS00691").fullname("Nguyễn Trường Huy 3").level(3).build(),
                Staff.builder().id("TS00692").fullname("Nguyễn Trường Huy 4").level(4).build(),
                Staff.builder().id("TS00693").fullname("Nguyễn Trường Huy 5").level(5).build()
        );
        model.addAttribute("list", staffList);
        return "list-status";
    }
}
