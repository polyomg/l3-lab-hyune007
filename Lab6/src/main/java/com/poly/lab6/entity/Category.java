package com.poly.lab6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Categories", schema = "webshop")
public class Category implements Serializable {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    @ToString.Exclude
    private List<Product> products;
}