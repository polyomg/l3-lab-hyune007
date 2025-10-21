package com.poly.lab7.repository;

import com.poly.lab7.entity.Product;
import com.poly.lab7.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Bài 1 - JPQL
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double min, double max);

    // Bài 2 - JPQL + phân trang
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // Bài 3 - Báo cáo tổng hợp
    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count "
            + "FROM Product o GROUP BY o.category ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();

    // Bài 4 - DSL
    List<Product> findByPriceBetween(double min, double max);

    // Bài 5 - DSL + phân trang
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}
