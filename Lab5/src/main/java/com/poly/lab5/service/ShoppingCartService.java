package com.poly.lab5.service;

import java.util.Collection;
import com.poly.lab5.entity.Item;

public interface ShoppingCartService {
    /**
     * Thêm một mặt hàng vào giỏ hàng.
     * @param id ID của mặt hàng
     * @return Mặt hàng đã được thêm
     */
    Item add(Integer id);

    /**
     * Xóa một mặt hàng khỏi giỏ hàng.
     * @param id ID của mặt hàng cần xóa
     */
    void remove(Integer id);

    /**
     * Cập nhật số lượng của một mặt hàng.
     * @param id ID của mặt hàng
     * @param quantity Số lượng mới
     * @return Mặt hàng đã được cập nhật
     */
    Item update(Integer id, int quantity);

    /**
     * Xóa tất cả các mặt hàng trong giỏ hàng.
     */
    void clear();

    /**
     * Lấy tất cả các mặt hàng trong giỏ hàng.
     * @return Collection các mặt hàng
     */
    Collection<Item> getItems();

    /**
     * Lấy tổng số lượng các mặt hàng.
     * @return Tổng số lượng
     */
    int getCount();

    /**
     * Lấy tổng số tiền của các mặt hàng.
     * @return Tổng số tiền
     */
    double getAmount();
}
