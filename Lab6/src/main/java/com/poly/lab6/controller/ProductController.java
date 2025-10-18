package com.poly.lab6.controller;

import com.poly.lab6.service.CategoryService;
import com.poly.lab6.service.ProductService;
import com.poly.lab6.entity.Product;
import com.poly.lab6.service.CategoryService;
import com.poly.lab6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productDAO;

    @Autowired
    CategoryService categoryDAO;

    // Hiển thị danh sách + form create
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("item", new Product());
        model.addAttribute("items", productDAO.findAll());
        model.addAttribute("categories", categoryDAO.findAll());
        return "product/index";
    }

    // Hiển thị form tạo (nếu cần template riêng)
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Product());
        model.addAttribute("categories", categoryDAO.findAll());
        return "product/create";
    }

    // Create
    @PostMapping("/create")
    public String create(Product item) {
        productDAO.save(item);
        return "redirect:/product/index";
    }

    // Update
    @PostMapping("/update")
    public String update(Product item) {
        productDAO.save(item);
        return "redirect:/product/edit/" + item.getId();
    }

    // Edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product item = productDAO.findById(id).get();
        model.addAttribute("item", item);
        model.addAttribute("items", productDAO.findAll());
        model.addAttribute("categories", categoryDAO.findAll());
        return "product/index";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productDAO.deleteById(id);
        return "redirect:/product/index";
    }
}