package com.poly.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String image;

    @Column(columnDefinition = "DECIMAL(10,2)")
    Double price;

    @Temporal(TemporalType.DATE)
    Date createdate = new Date();

    Boolean available;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    Category category;
}
