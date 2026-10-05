package com.fonehaus.app.model;

public class Customer {

    private String name;
    private String phoneNumber;
    private String email;
    private String deliveryAddress;

    public Customer(String name, String phoneNumber, String email, String deliveryAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerSummary() {
        return "Customer: " + name + "\nPhone: " + phoneNumber + "\nEmail: " + email + "\nAddress: " + deliveryAddress;
    }
}
