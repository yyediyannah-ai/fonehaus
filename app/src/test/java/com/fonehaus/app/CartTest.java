package com.fonehaus.app;

import com.fonehaus.app.model.Cart;
import com.fonehaus.app.model.IPhone;
import com.fonehaus.app.model.Phone;
import com.fonehaus.app.model.Product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartTest {

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    public void testAddAndRemoveProduct() {
        Product p1 = new Phone("Samsung Galaxy", 500.0, "Smart phone", "Phones");
        Product p2 = new IPhone("iPhone 14 Pro", 1200.0, "Apple iPhone", "iPhones");

        cart.addProduct(p1);
        cart.addProduct(p2);

        assertEquals(2, cart.getItemCount());
        assertEquals(1700.0, cart.calculateTotal(), 0.001);

        cart.removeProduct(p1);
        assertEquals(1, cart.getItemCount());
        assertEquals(1200.0, cart.calculateTotal(), 0.001);
    }

    @Test
    public void testClearCart() {
        cart.addProduct(new Product("Item 1", 100.0, "Desc", "Audio"));
        cart.addProduct(new Product("Item 2", 200.0, "Desc", "Audio"));

        assertEquals(2, cart.getItemCount());
        cart.clearCart();
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.calculateTotal(), 0.001);
    }
}
