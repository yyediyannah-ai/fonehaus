package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fonehaus.app.model.CartManager;

public class MainActivity extends AppCompatActivity {

    private Button btnCart;
    private EditText edtMainSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ==========================================
        // FIND VIEWS
        // ==========================================

        Button btnViewProducts = findViewById(R.id.btnViewProducts);
        Button btnCategories = findViewById(R.id.btnCategories);
        btnCart = findViewById(R.id.btnCart);

        edtMainSearch = findViewById(R.id.edtMainSearch);
        Button btnMainSearch = findViewById(R.id.btnMainSearch);

        TextView chipPhones = findViewById(R.id.chipPhones);
        TextView chipLaptops = findViewById(R.id.chipLaptops);
        TextView chipAudio = findViewById(R.id.chipAudio);
        TextView chipTVs = findViewById(R.id.chipTVs);

        // ==========================================
        // SEARCH HANDLERS
        // ==========================================

        Runnable triggerSearch = () -> {
            if (edtMainSearch != null) {
                String query = edtMainSearch.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                intent.putExtra("searchQuery", query);
                startActivity(intent);
            }
        };

        if (btnMainSearch != null) {
            btnMainSearch.setOnClickListener(v -> triggerSearch.run());
        }

        if (edtMainSearch != null) {
            edtMainSearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    triggerSearch.run();
                    return true;
                }
                return false;
            });
        }

        // ==========================================
        // VIEW PRODUCTS
        // ==========================================

        btnViewProducts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
            startActivity(intent);
        });

        // ==========================================
        // CATEGORIES
        // ==========================================

        btnCategories.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        });

        // ==========================================
        // SHOPPING CART
        // ==========================================

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // ==========================================
        // QUICK CATEGORY CHIP HANDLERS
        // ==========================================

        if (chipPhones != null) {
            chipPhones.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                intent.putExtra("category", "Phones");
                startActivity(intent);
            });
        }

        if (chipLaptops != null) {
            chipLaptops.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                intent.putExtra("category", "Laptops");
                startActivity(intent);
            });
        }

        if (chipAudio != null) {
            chipAudio.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                intent.putExtra("category", "Audio");
                startActivity(intent);
            });
        }

        if (chipTVs != null) {
            chipTVs.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                intent.putExtra("category", "Smart TVs");
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
