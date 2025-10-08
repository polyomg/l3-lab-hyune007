package com.poly.lab5.db;

import com.poly.lab5.entity.Item;

import java.util.HashMap;
import java.util.Map;

public class DB {
    public static final Map<Integer, Item> items = new HashMap<> ();

    static {
        items.put (1, new Item (1, "Iphone 12", 1200, 1));
        items.put (2, new Item (2, "Iphone 13", 1300.5, 1));
        items.put (3, new Item (3, "Iphone 14", 1400.5, 1));
        items.put (4, new Item (4, "Iphone 15", 1500, 1));
        items.put (5, new Item (5, "Iphone 16", 1600.9, 1));
    }
}
