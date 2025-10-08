package com.poly.lab5.service;

import java.util.Collection;
import com.poly.lab5.entity.Item;

public interface ShoppingCartService {
    Item add(Integer id);
    void remove(Integer id);
    Item update(Integer id, int quantity);
    void clear();
    Collection<Item> getItems();
    int getCount();
    double getAmount();
}
