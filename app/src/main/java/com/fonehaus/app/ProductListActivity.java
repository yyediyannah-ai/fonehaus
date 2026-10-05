package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fonehaus.app.data.ProductData;
import com.fonehaus.app.model.Product;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private LinearLayout productContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_list);

        productContainer = findViewById(R.id.productContainer);
        TextView txtTitle = findViewById(R.id.txtProductListTitle);

        // ==========================================
        // GET CATEGORY FILTER FROM INTENT
        // ==========================================

        String selectedCategory = getIntent().getStringExtra("category");

        if (txtTitle != null && selectedCategory != null && !selectedCategory.isEmpty()) {
            if (selectedCategory.equalsIgnoreCase("All")) {
                txtTitle.setText("All Products");
            } else if (selectedCategory.equalsIgnoreCase("Phones")) {
                txtTitle.setText("Smartphones");
            } else {
                txtTitle.setText(selectedCategory);
            }
        }

        // ==========================================
        // GET FILTERED PRODUCTS
        // ==========================================

        ArrayList<Product> products = ProductData.getProductsByCategory(selectedCategory);

        // ==========================================
        // DISPLAY PRODUCTS
        // ==========================================

        if (products.isEmpty()) {
            TextView emptyText = new TextView(this);
            emptyText.setText("No products available in this category.");
            emptyText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.body_text_size));
            emptyText.setTextColor(getColor(R.color.text_secondary));
            emptyText.setPadding(0, 32, 0, 32);
            productContainer.addView(emptyText);
        } else {
            for (Product product : products) {
                addProductToScreen(product);
            }
        }

        // ==========================================
        // CART BUTTON
        // ==========================================

        Button btnCart = findViewById(R.id.btnProductListCart);

        if (btnCart != null) {
            btnCart.setOnClickListener(v -> {
                Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }
    }

    // ==========================================
    // ADD RESPONSIVE PRODUCT CARD TO SCREEN
    // ==========================================

    private void addProductToScreen(Product product) {

        int cardPadding = getResources().getDimensionPixelSize(R.dimen.card_padding);
        int imageHeight = getResources().getDimensionPixelSize(R.dimen.product_image_height);
        int cornerRadius = getResources().getDimensionPixelSize(R.dimen.button_corner_radius);

        // Container Card
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(cardPadding, cardPadding, cardPadding, cardPadding);
        card.setBackgroundColor(0xFFF5F5F5); // Light card container on white background

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, cardPadding);
        card.setLayoutParams(cardParams);

        // Product Image
        ImageView img = new ImageView(this);
        if (product.getImageResId() > 0) {
            img.setImageResource(product.getImageResId());
        } else {
            img.setImageResource(R.drawable.welcome_bg_devices);
        }

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                imageHeight
        );
        imgParams.setMargins(0, 0, 0, cardPadding / 2);
        img.setLayoutParams(imgParams);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        card.addView(img);

        // Product Name
        TextView name = new TextView(this);
        name.setText(product.getProductName());
        name.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_text_size));
        name.setTypeface(null, android.graphics.Typeface.BOLD);
        name.setTextColor(getColor(R.color.text_primary));
        card.addView(name);

        // Price
        TextView price = new TextView(this);
        price.setText("K" + String.format("%.2f", product.getPrice()));
        price.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.body_text_size));
        price.setTypeface(null, android.graphics.Typeface.BOLD);
        price.setTextColor(getColor(R.color.fone_yellow_text));
        price.setPadding(0, cardPadding / 4, 0, cardPadding / 4);
        card.addView(price);

        // Description snippet
        TextView desc = new TextView(this);
        desc.setText(product.getDescription());
        desc.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.chip_text_size));
        desc.setTextColor(getColor(R.color.text_secondary));
        desc.setMaxLines(2);
        desc.setPadding(0, 0, 0, cardPadding / 2);
        card.addView(desc);

        // Action Button
        MaterialButton detailsButton = new MaterialButton(this);
        detailsButton.setText("View Details");
        detailsButton.setAllCaps(false);
        detailsButton.setTextColor(getColor(R.color.black));
        detailsButton.setBackgroundTintList(getColorStateList(R.color.fone_yellow));
        detailsButton.setStrokeWidth(0);
        detailsButton.setCornerRadius(cornerRadius);

        card.addView(detailsButton);

        productContainer.addView(card);

        // Open details on click
        detailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
            intent.putExtra("productName", product.getProductName());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("description", product.getDescription());
            intent.putExtra("category", product.getCategory());
            intent.putExtra("imageResId", product.getImageResId());
            startActivity(intent);
        });
    }
}
