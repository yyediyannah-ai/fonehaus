package com.fonehaus.app.model;

public class CartManager {

    // ==========================================
    // ONE CART USED THROUGHOUT THE APP
    // ==========================================

    private static Cart cart = new Cart();

    private CartManager() {
        // Prevent creating multiple CartManager objects
    }

    public static Cart getCart() {
        return cart;
    }
}
