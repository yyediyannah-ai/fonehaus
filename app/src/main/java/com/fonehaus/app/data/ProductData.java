package com.fonehaus.app.data;

import com.fonehaus.app.R;
import com.fonehaus.app.model.IPhone;
import com.fonehaus.app.model.Phone;
import com.fonehaus.app.model.Product;

import java.util.ArrayList;

public class ProductData {

    // ==================================================
    // OFFICIAL FONE HAUS PRODUCTS CATALOG
    // ==================================================

    public static ArrayList<Product> getAllProducts() {

        ArrayList<Product> products = new ArrayList<>();

        // --- SAMSUNG PHONES ---
        products.add(
                new Phone(
                        "SAMSUNG GALAXY A07",
                        549.00,
                        "Compact, affordable Samsung smartphone with HD display and long-lasting battery.",
                        "Phones",
                        R.drawable.img_samsung_a07
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY A17",
                        999.00,
                        "Sleek Galaxy A17 featuring crisp camera clarity and fast processing performance.",
                        "Phones",
                        R.drawable.img_samsung_a17
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG A26 5G",
                        1219.00,
                        "High-speed 5G mobile connectivity with vivid AMOLED screen and multi-lens camera.",
                        "Phones",
                        R.drawable.img_samsung_a26
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY A27",
                        1699.00,
                        "Premium Galaxy A-series smartphone with advanced night camera and fast charging.",
                        "Phones",
                        R.drawable.img_samsung_a27
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY A36 5G",
                        1699.00,
                        "Vibrant Lime edition Galaxy A36 with 5G speed and water-resistant glass design.",
                        "Phones",
                        R.drawable.img_samsung_a36
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY A37",
                        2099.00,
                        "Next-gen A-series Galaxy phone with super smooth 120Hz display and stereo sound.",
                        "Phones",
                        R.drawable.img_samsung_a37
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY A56 5G",
                        2159.00,
                        "Powerful Galaxy A56 5G with pro-grade camera, OIS stabilization, and IP67 rating.",
                        "Phones",
                        R.drawable.img_samsung_a56
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY A57",
                        2799.00,
                        "Top-tier A-series performance with sleek metal frame and all-day battery life.",
                        "Phones",
                        R.drawable.img_samsung_a57
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY S25",
                        3899.00,
                        "Flagship Samsung Galaxy S25 with Snapdragon processor and AI photo editing.",
                        "Phones",
                        R.drawable.img_samsung_s25
                )
        );

        products.add(
                new Phone(
                        "SAMSUNG GALAXY S26 ULTRA",
                        5499.00,
                        "Ultimate Galaxy flagship featuring 200MP camera zoom, S Pen, and Titanium body.",
                        "Phones",
                        R.drawable.img_samsung_s26_ultra
                )
        );

        // --- IPHONES ---
        products.add(
                new IPhone(
                        "Apple iPhone 14 Pro Max",
                        4299.00,
                        "Apple iPhone 14 Pro Max with Dynamic Island, 48MP camera, and Always-On display.",
                        "iPhones",
                        R.drawable.img_apple_iphone_14_pro
                )
        );

        // --- TABLETS ---
        products.add(
                new Product(
                        "SAMSUNG GALAXY TAB A11",
                        899.00,
                        "Versatile Samsung tablet for work and study with quad speakers and slim aluminum body.",
                        "Tablets",
                        R.drawable.img_samsung_tab_a11
                )
        );

        products.add(
                new Product(
                        "SAMSUNG GALAXY TAB S10 ULTRA 5G",
                        6799.00,
                        "Flagship 14.6-inch AMOLED 5G tablet with S Pen stylus included.",
                        "Tablets",
                        R.drawable.img_samsung_tab_s10
                )
        );

        // --- LAPTOPS ---
        products.add(
                new Product(
                        "INFINIX InBook Air Laptop",
                        2499.00,
                        "Ultra-lightweight laptop with Full HD screen, fast SSD storage, and metal finish.",
                        "Laptops",
                        R.drawable.img_infinix_inbook_air
                )
        );

        // --- AUDIO & HEADPHONES ---
        products.add(
                new Product(
                        "JBL Tune On-Ear Wired Headphones",
                        199.00,
                        "Powerful JBL Pure Bass sound headphones with comfortable padded ear cushions.",
                        "Audio",
                        R.drawable.img_jbl_headphones
                )
        );

        products.add(
                new Product(
                        "JBL Stage 320 Bluetooth Speaker",
                        1199.00,
                        "High-power portable JBL party speaker with light show and deep bass resonance.",
                        "Audio",
                        R.drawable.img_jbl_speaker
                )
        );

        // --- SMART TVS ---
        products.add(
                new Product(
                        "Tesla 32-inch Smart TV",
                        899.00,
                        "High-definition 32-inch Smart TV with built-in Wi-Fi, streaming apps, and HDMI.",
                        "Smart TVs",
                        R.drawable.img_tesla_tv
                )
        );

        // --- SMART WATCHES ---
        products.add(
                new Product(
                        "XWATCH H5 Pro Smartwatch",
                        299.00,
                        "Smart fitness watch with health tracking, Bluetooth calling, and custom watch faces.",
                        "Smart Watches",
                        R.drawable.img_xwatch_h5
                )
        );

        return products;
    }

    public static ArrayList<Product> getPhoneProducts() {
        return getProductsByCategory("Phones");
    }

    public static ArrayList<Product> getProductsByCategory(String category) {

        ArrayList<Product> filtered = new ArrayList<>();
        ArrayList<Product> all = getAllProducts();

        if (category == null || category.trim().isEmpty() || category.equalsIgnoreCase("All")) {
            return all;
        }

        for (Product product : all) {
            if (product.getCategory().equalsIgnoreCase(category)
                    || (category.equalsIgnoreCase("Phones") && product instanceof Phone)) {
                filtered.add(product);
            }
        }

        return filtered;
    }
}
