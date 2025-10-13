package com.poly.lab6.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.Product;

public interface ProductService extends JpaRepository<Product, Integer> {
}
