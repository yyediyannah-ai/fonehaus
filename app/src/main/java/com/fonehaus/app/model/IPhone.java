package com.fonehaus.app.model;

public class IPhone extends Phone {

    // ==============================
    // CONSTRUCTORS
    // ==============================

    public IPhone(String productName,
                  double price,
                  String description,
                  String category) {

        super(productName, price, description, category, 0);
    }

    public IPhone(String productName,
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
        return "iPhone: " + getProductName()
                + " - K" + String.format("%.2f", getPrice());
    }
}
