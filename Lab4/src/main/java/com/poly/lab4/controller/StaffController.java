package com.poly.lab4.controller;

import com.poly.lab4.entity.Staff;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StaffController {
    @RequestMapping("/staff/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff stf) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên");
        return "staff-create";
    }

    @RequestMapping("/staff/create/save")
    public String createSave(Model model, @RequestPart("photo_file")MultipartFile photoFile, @Valid @ModelAttribute("staff") Staff staff, Errors  errors){
        if (!photoFile.isEmpty()){
            staff.setPhoto(photoFile.getName());
        }
        if (errors.hasErrors()){
            model.addAttribute("message", "Vui lòng kiểm tra lại thông tin");
        } else {
            model.addAttribute("message", "Thông tin đã nhập đúng!");
        }
        return "staff-create";
    }
}