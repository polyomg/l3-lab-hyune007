package com.poly.lab6.controller;

import com.poly.lab6.service.ProductService;
import com.poly.lab6.entity.Product;
import com.poly.lab6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductSortController {

    @Autowired
    private ProductService dao;

    @GetMapping("/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {

        String sortField = field.orElse("price");

        Sort sort = Sort.by(Direction.DESC, sortField);

        List<Product> items = dao.findAll(sort);

        model.addAttribute("items", items);
        model.addAttribute("field", sortField.toUpperCase());

        return "product/sort";
    }
}