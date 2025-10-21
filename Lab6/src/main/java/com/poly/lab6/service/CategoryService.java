package com.poly.lab6.service;

import com.poly.lab6.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryService extends JpaRepository<Category, String> {
}
