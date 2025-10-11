package com.poly.lab5.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.lab5.db.DB;
import com.poly.lab5.entity.Item;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Map<Integer, Item> cart = new HashMap<>();

    /**
     * Thêm một mặt hàng vào giỏ hàng hoặc tăng số lượng nếu đã tồn tại.
     * @param id ID của mặt hàng cần thêm
     * @return Mặt hàng đã được thêm hoặc cập nhật
     */
    @Override
    public Item add(Integer id) {
        if (cart.containsKey(id)) {
            Item item = cart.get(id);
            item.setQuantity(item.getQuantity() + 1);
            return item;
        } else {
            Item itemFromDB = DB.items.get(id);
            if (itemFromDB != null) {
                // Tạo một bản sao của sản phẩm từ DB và đặt số lượng là 1
                Item newItem = new Item(itemFromDB.getId(), itemFromDB.getName(), itemFromDB.getPrice(), 1);
                cart.put(id, newItem);
                return newItem;
            } else {
                throw new IllegalArgumentException("Product with id " + id + " not found in DB");
            }
        }
    }

    /**
     * Xóa một mặt hàng khỏi giỏ hàng.
     * @param id ID của mặt hàng cần xóa
     */
    @Override
    public void remove(Integer id) {
        cart.remove(id);
    }

    /**
     * Cập nhật số lượng của một mặt hàng trong giỏ hàng.
     * @param id ID của mặt hàng cần cập nhật
     * @param quantity Số lượng mới
     * @return Mặt hàng đã được cập nhật
     */
    @Override
    public Item update(Integer id, int quantity) {
        if (cart.containsKey(id)) {
            Item item = cart.get(id);
            item.setQuantity(quantity);
            return item;
        }
        throw new IllegalArgumentException("Item not found in the cart!");
    }

    /**
     * Xóa tất cả các mặt hàng khỏi giỏ hàng.
     */
    @Override
    public void clear() {
        cart.clear();
    }

    /**
     * Lấy tất cả các mặt hàng trong giỏ hàng.
     * @return Một Collection chứa các mặt hàng
     */
    @Override
    public Collection<Item> getItems() {
        return cart.values();
    }

    /**
     * Lấy tổng số lượng các mặt hàng trong giỏ hàng.
     * @return Tổng số lượng
     */
    @Override
    public int getCount() {
        return cart.values().stream().mapToInt(Item::getQuantity).sum();
    }

    /**
     * Lấy tổng thành tiền của tất cả các mặt hàng trong giỏ hàng.
     * @return Tổng thành tiền
     */
    @Override
    public double getAmount() {
        return cart.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
