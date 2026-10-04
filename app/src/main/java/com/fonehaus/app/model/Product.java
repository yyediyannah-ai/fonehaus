package com.fonehaus.app.model;

public class Product {

    // ==============================
    // EDITABLE PRODUCT INFORMATION
    // ==============================

    private String productName;
    private double price;
    private String description;
    private String category;
    private int imageResId;

    // ==============================
    // CONSTRUCTORS
    // ==============================

    public Product(String productName,
                   double price,
                   String description,
                   String category) {

        this(productName, price, description, category, 0);
    }

    public Product(String productName,
                   double price,
                   String description,
                   String category,
                   int imageResId) {

        this.productName = productName;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageResId = imageResId;
    }

    // ==============================
    // GETTERS
    // ==============================

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getImageResId() {
        return imageResId;
    }

    // ==============================
    // SETTERS
    // ==============================

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    // ==============================
    // METHOD
    // ==============================

    public String displayProduct() {
        return productName + " - K" + String.format("%.2f", price);
    }
}
