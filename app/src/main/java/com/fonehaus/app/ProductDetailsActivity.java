package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fonehaus.app.model.CartManager;
import com.fonehaus.app.model.IPhone;
import com.fonehaus.app.model.Phone;
import com.fonehaus.app.model.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_details);

        // ==========================================
        // GET PRODUCT INFORMATION
        // ==========================================

        String productName =
                getIntent().getStringExtra("productName");

        double price =
                getIntent().getDoubleExtra("price", 0.00);

        String description =
                getIntent().getStringExtra("description");

        String category =
                getIntent().getStringExtra("category");

        int imageResId =
                getIntent().getIntExtra("imageResId", 0);

        // ==========================================
        // DISPLAY INFORMATION
        // ==========================================

        TextView txtName = findViewById(R.id.txtProductName);
        TextView txtPrice = findViewById(R.id.txtProductPrice);
        TextView txtDescription = findViewById(R.id.txtProductDescription);
        TextView txtCategory = findViewById(R.id.txtProductCategory);
        ImageView imgDetails = findViewById(R.id.imgProductDetails);

        if (txtName != null) txtName.setText(productName);
        if (txtPrice != null) txtPrice.setText("K" + String.format("%.2f", price));
        if (txtDescription != null) txtDescription.setText(description);
        if (txtCategory != null) txtCategory.setText(category);

        if (imgDetails != null && imageResId > 0) {
            imgDetails.setImageResource(imageResId);
        }

        // ==========================================
        // ADD TO CART
        // ==========================================

        Button btnAddToCart = findViewById(R.id.btnAddToCart);

        if (btnAddToCart != null) {
            btnAddToCart.setOnClickListener(v -> {

                Product product;

                if (category != null && category.equalsIgnoreCase("iPhones")) {
                    product = new IPhone(productName, price, description, category, imageResId);
                } else if (category != null && category.equalsIgnoreCase("Phones")) {
                    product = new Phone(productName, price, description, category, imageResId);
                } else {
                    product = new Product(productName, price, description, category, imageResId);
                }

                CartManager.getCart().addProduct(product);

                Toast.makeText(
                        ProductDetailsActivity.this,
                        "Product added to cart",
                        Toast.LENGTH_SHORT
                ).show();
            });
        }

        // ==========================================
        // VIEW CART
        // ==========================================

        Button btnViewCart = findViewById(R.id.btnDetailsCart);

        if (btnViewCart != null) {
            btnViewCart.setOnClickListener(v -> {
                Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }
    }
}
