package com.poly.lab6.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.Order;

public interface OrderService extends JpaRepository<Order, Long> {
}
