package com.fonehaus.app.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Order {

    private String orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalAmount;
    private String paymentMethod;
    private String orderDate;
    private String status;

    public Order(String orderId, Customer customer, Cart cart, String paymentMethod) {
        this.orderId = orderId;
        this.customer = customer;
        // Make a copy of cart products for this order
        this.products = new ArrayList<>(cart.getProducts());
        this.totalAmount = cart.calculateTotal();
        this.paymentMethod = paymentMethod;
        this.orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(new Date());
        this.status = "Confirmed";
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Date: ").append(orderDate).append("\n");
        sb.append("Payment: ").append(paymentMethod).append("\n");
        sb.append("Status: ").append(status).append("\n\n");
        sb.append(customer.getCustomerSummary()).append("\n\n");
        sb.append("Purchased Items:\n");

        for (Product product : products) {
            // Polymorphic method call displayProduct()
            sb.append("- ").append(product.displayProduct()).append("\n");
        }

        sb.append("\nTotal Paid: K").append(String.format(Locale.US, "%.2f", totalAmount));
        return sb.toString();
    }
}
