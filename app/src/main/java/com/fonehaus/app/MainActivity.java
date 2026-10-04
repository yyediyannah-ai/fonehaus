package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fonehaus.app.model.CartManager;

public class MainActivity extends AppCompatActivity {

    private Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ==========================================
        // FIND BUTTONS & CHIPS
        // ==========================================

        Button btnViewProducts = findViewById(R.id.btnViewProducts);
        Button btnCategories = findViewById(R.id.btnCategories);
        btnCart = findViewById(R.id.btnCart);

        TextView chipPhones = findViewById(R.id.chipPhones);
        TextView chipLaptops = findViewById(R.id.chipLaptops);
        TextView chipAudio = findViewById(R.id.chipAudio);
        TextView chipTVs = findViewById(R.id.chipTVs);

        // ==========================================
        // VIEW PRODUCTS
        // ==========================================

        btnViewProducts.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MainActivity.this,
                            ProductListActivity.class);

            startActivity(intent);
        });

        // ==========================================
        // CATEGORIES
        // ==========================================

        btnCategories.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MainActivity.this,
                            CategoryActivity.class);

            startActivity(intent);
        });

        // ==========================================
        // SHOPPING CART
        // ==========================================

        btnCart.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MainActivity.this,
                            CartActivity.class);

            startActivity(intent);
        });

        // ==========================================
        // QUICK CATEGORY CHIP HANDLERS
        // ==========================================

        if (chipPhones != null) {
            chipPhones.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                startActivity(intent);
            });
        }

        if (chipLaptops != null) {
            chipLaptops.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            });
        }

        if (chipAudio != null) {
            chipAudio.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            });
        }

        if (chipTVs != null) {
            chipTVs.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Update live cart item count
        if (btnCart != null) {
            int count = CartManager.getCart().getItemCount();
            if (count > 0) {
                btnCart.setText("Shopping Cart (" + count + " items)");
            } else {
                btnCart.setText("Shopping Cart");
            }
        }
    }
}
