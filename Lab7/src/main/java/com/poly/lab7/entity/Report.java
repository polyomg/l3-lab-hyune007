package com.poly.lab7.entity;

public interface Report {

    Category getGroup(); // Group by category - changed from Serializable to Category

    Double getSum();        // Total price for the category

    Long getCount();        // Count of products in the category
}
