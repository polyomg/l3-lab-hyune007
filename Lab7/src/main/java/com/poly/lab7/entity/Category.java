package com.poly.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    String id;
    String name;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
