package com.poly.lab2.controller;

import com.poly.lab2.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    private static final String DEFAULT_PRODUCT_NAME = "iPhone 30";
    private static final Double DEFAULT_PRODUCT_PRICE = 5000.0;

    private static List<Product> savedProducts = new ArrayList<>();

    @GetMapping("/product/form")
    public String form(Model model, @RequestParam(name = "page",defaultValue = "0") int page) {
        Product p = createDefaultProduct();
        model.addAttribute("product", p);

        List<Product> all = new ArrayList<>();
        all.addAll(getOriginalItems());
        all.addAll(savedProducts);

        int pageSize = 5;
        int totalPages = (int) Math.ceil((double) Math.max(1, all.size()) / pageSize);
        page = Math.min(page, Math.max(0, totalPages - 1));

        int fromIndex = page * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, all.size());

        fromIndex = Math.min(fromIndex, all.size());

        List<Product> subList = all.subList(fromIndex, toIndex);

        model.addAttribute("items", subList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", Math.max(1, totalPages));

        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute Product product, Model model, RedirectAttributes ps) {
        savedProducts.add(product);
        ps.addFlashAttribute("products", product);
        model.addAttribute("product", createDefaultProduct());
        return "redirect:/product/form";
    }

    private Product createDefaultProduct() {
        Product defaultProduct = new Product();
        defaultProduct.setName(DEFAULT_PRODUCT_NAME);
        defaultProduct.setPrice(DEFAULT_PRODUCT_PRICE);
        return defaultProduct;
    }

    public List<Product> getOriginalItems() {
        return Arrays.asList(
                new Product("Product A", 1.0),
                new Product("Product B", 12.0),
                new Product("Product C", 23.5),
                new Product("Product D", 34.0),
                new Product("Product E", 45.5),
                new Product("Product F", 56.0),
                new Product("Product G", 67.5),
                new Product("Product H", 78.0),
                new Product("Product I", 89.5),
                new Product("Product J", 100.0),
                new Product("Product K", 111.5),
                new Product("Product L", 122.0),
                new Product("Product M", 133.5),
                new Product("Product N", 144.0),
                new Product("Product O", 155.5),
                new Product("Product P", 166.0),
                new Product("Product Q", 177.5),
                new Product("Product R", 188.0),
                new Product("Product S", 199.5),
                new Product("Product T", 210.0)
        );
    }

    @ModelAttribute("items")
    public List<Product> getItems() {
        List<Product> allItems = new ArrayList<>();
        allItems.addAll(getOriginalItems());
        allItems.addAll(savedProducts);
        return allItems;
    }
}
