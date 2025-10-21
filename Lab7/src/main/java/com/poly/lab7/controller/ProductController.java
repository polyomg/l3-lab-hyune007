package com.poly.lab7.controller;

import java.util.List;
import java.util.Optional;

import com.poly.lab7.service.ProductService;
import com.poly.lab7.entity.Product;
import com.poly.lab7.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService dao;

    @Autowired
    SessionService session;

    // Bài 1 - tìm theo khoảng giá
    @RequestMapping("/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPrice(minPrice, maxPrice);
        // Số thự tự của mỗi sản phẩm
        model.addAttribute("index", 0);
        model.addAttribute("items", items);
        return "product/search";
    }

    // Bài 2 - tìm theo từ khóa + phân trang
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {

        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);
        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);
        return "product/search-and-page";
    }
}
