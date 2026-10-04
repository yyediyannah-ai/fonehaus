package com.fonehaus.app.model;

import java.util.ArrayList;

public class Cart {

    // ==========================================
    // ARRAYLIST STORES PRODUCTS IN THE CART
    // ==========================================

    private ArrayList<Product> products;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public Cart() {
        products = new ArrayList<>();
    }

    // ==========================================
    // ADD PRODUCT
    // ==========================================

    public void addProduct(Product product) {
        products.add(product);
    }

    // ==========================================
    // REMOVE PRODUCT
    // ==========================================

    public void removeProduct(Product product) {
        products.remove(product);
    }

    // ==========================================
    // GET PRODUCTS
    // ==========================================

    public ArrayList<Product> getProducts() {
        return products;
    }

    // ==========================================
    // CALCULATE TOTAL
    // ==========================================

    public double calculateTotal() {

        double total = 0.0;

        for (Product product : products) {
            total += product.getPrice();
        }

        return total;
    }

    // ==========================================
    // CLEAR CART
    // ==========================================

    public void clearCart() {
        products.clear();
    }

    // ==========================================
    // NUMBER OF PRODUCTS
    // ==========================================

    public int getItemCount() {
        return products.size();
    }
}
