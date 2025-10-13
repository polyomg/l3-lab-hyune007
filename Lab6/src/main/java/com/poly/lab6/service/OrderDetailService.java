package com.poly.lab6.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.OrderDetail;

public interface OrderDetailService extends JpaRepository<OrderDetail, Long> {
}