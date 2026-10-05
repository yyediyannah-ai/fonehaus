package com.fonehaus.app;

import com.fonehaus.app.model.Cart;
import com.fonehaus.app.model.Customer;
import com.fonehaus.app.model.Order;
import com.fonehaus.app.model.Phone;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CustomerOrderTest {

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer("John Doe", "+675 7000 1234", "john@example.com", "Section 12, Port Moresby");

        assertEquals("John Doe", customer.getName());
        assertEquals("+675 7000 1234", customer.getPhoneNumber());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("Section 12, Port Moresby", customer.getDeliveryAddress());
        assertTrue(customer.getCustomerSummary().contains("John Doe"));
    }

    @Test
    public void testOrderCreationAndSummary() {
        Customer customer = new Customer("Jane Smith", "+675 7111 2222", "jane@example.com", "Lae, Morobe");
        Cart cart = new Cart();
        cart.addProduct(new Phone("Samsung A07", 549.0, "Budget phone", "Phones"));

        Order order = new Order("FH-12345", customer, cart, "Cash on Delivery");

        assertEquals("FH-12345", order.getOrderId());
        assertEquals(549.0, order.getTotalAmount(), 0.001);
        assertEquals("Cash on Delivery", order.getPaymentMethod());
        assertNotNull(order.getOrderDate());

        String summary = order.getOrderSummary();
        assertTrue(summary.contains("FH-12345"));
        assertTrue(summary.contains("Jane Smith"));
        assertTrue(summary.contains("Samsung A07"));
        assertTrue(summary.contains("549.00"));
    }
}
