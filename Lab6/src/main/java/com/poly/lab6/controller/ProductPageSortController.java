package com.poly.lab6.controller;

import com.poly.lab6.service.ProductService;
import com.poly.lab6.entity.Product;
import com.poly.lab6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductPageSortController {

    @Autowired
    private ProductService dao;

    // Phân trang và sắp xếp
    @GetMapping("/page-sort")
    public String paginateSort(Model model,
                               @RequestParam("p") Optional<Integer> p,
                               @RequestParam("field") Optional<String> field) {

        // Mỗi trang 5 sản phẩm, mặc định page=0
        int pageNumber = p.orElse(0);
        int pageNumberReal = pageNumber + 1;
        // Sắp xếp giảm dần theo cột field, mặc định theo price
        String sortField = field.orElse("price");
        Sort sort = Sort.by(Sort.Direction.DESC, sortField);

        Pageable pageable = PageRequest.of(pageNumber, 5, sort);
        Page<Product> page = dao.findAll(pageable);

        model.addAttribute("page", page);
        model.addAttribute("field", sortField.toUpperCase());
        model.addAttribute ("current", pageNumberReal);
        return "product/page-sort";
    }
}