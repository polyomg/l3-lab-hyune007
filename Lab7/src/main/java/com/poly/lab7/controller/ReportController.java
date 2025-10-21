package com.poly.lab7.controller;

import java.util.List;

import com.poly.lab7.entity.Report;
import com.poly.lab7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ProductService dao;

    @RequestMapping("/inventory-by-category")
    public String inventory(Model model) {
        List<Report> items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        return "report/inventory-by-category";
    }
}
