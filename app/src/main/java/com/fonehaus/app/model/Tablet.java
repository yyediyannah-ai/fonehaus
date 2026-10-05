package com.fonehaus.app.model;

import java.util.Locale;

public class Tablet extends Product {

    public Tablet(String productName,
                  double price,
                  String description,
                  String category) {
        super(productName, price, description, category, 0);
    }

    public Tablet(String productName,
                  double price,
                  String description,
                  String category,
                  int imageResId) {
        super(productName, price, description, category, imageResId);
    }

    // ==============================
    // METHOD OVERRIDING (POLYMORPHISM)
    // ==============================

    @Override
    public String displayProduct() {
        return "Tablet: " + getProductName()
                + " - K" + String.format(Locale.US, "%.2f", getPrice());
    }

    @Override
    public String getCategoryDetails() {
        return "Category: Tablet Device | " + getDescription();
    }
}
