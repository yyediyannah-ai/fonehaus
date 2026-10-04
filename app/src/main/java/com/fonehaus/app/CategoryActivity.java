package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);

        bindCategoryButton(R.id.btnPhones, "Phones");
        bindCategoryButton("btnTablets", "Tablets");
        bindCategoryButton("btnLaptops", "Laptops");
        bindCategoryButton("btnAudio", "Audio");
        bindCategoryButton("btnTVs", "Smart TVs");
        bindCategoryButton("btnWatches", "Smart Watches");
        bindCategoryButton("btnAllProducts", "All");

        Button btnCart = findViewById(R.id.btnCategoryCart);
        if (btnCart != null) {
            btnCart.setOnClickListener(v -> {
                Intent intent = new Intent(CategoryActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }
    }

    private void bindCategoryButton(int resId, String category) {
        Button btn = findViewById(resId);
        if (btn != null) {
            btn.setOnClickListener(v -> openCategory(category));
        }
    }

    private void bindCategoryButton(String idName, String category) {
        int resId = getResources().getIdentifier(idName, "id", getPackageName());
        if (resId != 0) {
            bindCategoryButton(resId, category);
        }
    }

    private void openCategory(String category) {
        Intent intent = new Intent(CategoryActivity.this, ProductListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
