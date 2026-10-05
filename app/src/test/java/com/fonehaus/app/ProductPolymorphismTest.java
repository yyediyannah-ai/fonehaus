package com.fonehaus.app;

import com.fonehaus.app.model.IPhone;
import com.fonehaus.app.model.Laptop;
import com.fonehaus.app.model.Phone;
import com.fonehaus.app.model.Product;
import com.fonehaus.app.model.Tablet;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductPolymorphismTest {

    @Test
    public void testInheritanceHierarchy() {
        Product p = new Product("Generic Gadget", 100.0, "Test gadget", "Audio");
        Phone phone = new Phone("Samsung Galaxy", 500.0, "Smart phone", "Phones");
        IPhone iPhone = new IPhone("iPhone 14 Pro", 1000.0, "Apple iPhone", "iPhones");
        Tablet tablet = new Tablet("Galaxy Tab", 800.0, "Tablet", "Tablets");
        Laptop laptop = new Laptop("Infinix InBook", 2000.0, "Laptop", "Laptops");

        assertTrue(phone instanceof Product);
        assertTrue(iPhone instanceof Phone);
        assertTrue(iPhone instanceof Product);
        assertTrue(tablet instanceof Product);
        assertTrue(laptop instanceof Product);
    }

    @Test
    public void testPolymorphicDisplayProduct() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Generic Product", 100.0, "Desc", "Cat"));
        products.add(new Phone("Galaxy Phone", 500.0, "Desc", "Phones"));
        products.add(new IPhone("iPhone 14", 1200.0, "Desc", "iPhones"));
        products.add(new Tablet("Tab S10", 900.0, "Desc", "Tablets"));
        products.add(new Laptop("InBook Air", 1500.0, "Desc", "Laptops"));

        assertEquals("Product: Generic Product - K100.00", products.get(0).displayProduct());
        assertEquals("Phone: Galaxy Phone - K500.00", products.get(1).displayProduct());
        assertEquals("iPhone: iPhone 14 - K1200.00", products.get(2).displayProduct());
        assertEquals("Tablet: Tab S10 - K900.00", products.get(3).displayProduct());
        assertEquals("Laptop: InBook Air - K1500.00", products.get(4).displayProduct());
    }

    @Test
    public void testPolymorphicCategoryDetails() {
        Product p1 = new Phone("Model A", 300.0, "Fast phone", "Phones");
        Product p2 = new IPhone("Model B", 900.0, "Pro camera", "iPhones");

        assertTrue(p1.getCategoryDetails().contains("Mobile Phone"));
        assertTrue(p2.getCategoryDetails().contains("Apple iOS Smartphone"));
    }
}
