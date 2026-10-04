package com.fonehaus.app.model;

public class Phone extends Product {

    // ==============================
    // CONSTRUCTORS
    // ==============================

    public Phone(String productName,
                 double price,
                 String description,
                 String category) {

        super(productName, price, description, category, 0);
    }

    public Phone(String productName,
                 double price,
                 String description,
                 String category,
                 int imageResId) {

        super(productName, price, description, category, imageResId);
    }

    // ==============================
    // METHOD OVERRIDING
    // ==============================

    @Override
    public String displayProduct() {
        return "Phone: " + getProductName()
                + " - K" + String.format("%.2f", getPrice());
    }
}
