package com.poly.lab8.controller;

import com.poly.lab8.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("mail", new MailService.Mail());
        return "mail/form";
    }

    @PostMapping("/send")
    public String send(Model model, @ModelAttribute("mail") MailService.Mail mail) {
        mailService.send(mail);
        model.addAttribute("message", "Đã gửi mail thành công!");
        return "mail/form";
    }

    @PostMapping("/queue")
    public String queue(Model model, @ModelAttribute("mail") MailService.Mail mail) {
        mailService.push(mail);
        model.addAttribute("message", "Mail đã được xếp vào hàng đợi!");
        return "mail/form";
    }
}
