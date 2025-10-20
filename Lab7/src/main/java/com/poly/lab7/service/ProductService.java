package com.poly.lab7.service;

import com.poly.lab7.entity.Product;
import com.poly.lab7.entity.Report;
import com.poly.lab7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // CRUD operations
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    // Bài 1 - Tìm sản phẩm theo khoảng giá (JPQL)
    public List<Product> findByPrice(double minPrice, double maxPrice) {
        return productRepository.findByPrice(minPrice, maxPrice);
    }

    // Bài 2 - Tìm sản phẩm theo từ khóa với phân trang (JPQL)
    public Page<Product> findByKeywords(String keywords, Pageable pageable) {
        return productRepository.findByKeywords(keywords, pageable);
    }

    // Bài 3 - Báo cáo tổng hợp theo danh mục
    public List<Report> getInventoryByCategory() {
        return productRepository.getInventoryByCategory();
    }

    // Bài 4 - Tìm sản phẩm theo khoảng giá (DSL)
    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Bài 5 - Tìm sản phẩm theo tên với phân trang (DSL)
    public Page<Product> findAllByNameLike(String keywords, Pageable pageable) {
        return productRepository.findAllByNameLike(keywords, pageable);
    }
}
