package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fonehaus.app.data.ProductData;
import com.fonehaus.app.model.Product;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private LinearLayout productContainer;
    private EditText edtProductListSearch;
    private TextView btnClearProductSearch;
    private String selectedCategory = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_list);

        productContainer = findViewById(R.id.productContainer);
        TextView txtTitle = findViewById(R.id.txtProductListTitle);
        edtProductListSearch = findViewById(R.id.edtProductListSearch);
        btnClearProductSearch = findViewById(R.id.btnClearProductSearch);

        // ==========================================
        // GET INTENT EXTRAS
        // ==========================================

        if (getIntent().hasExtra("category")) {
            String cat = getIntent().getStringExtra("category");
            if (cat != null && !cat.trim().isEmpty()) {
                selectedCategory = cat;
            }
        }

        String initialSearchQuery = getIntent().getStringExtra("searchQuery");

        if (txtTitle != null) {
            if (selectedCategory.equalsIgnoreCase("All")) {
                txtTitle.setText("All Products");
            } else if (selectedCategory.equalsIgnoreCase("Phones")) {
                txtTitle.setText("Smartphones");
            } else {
                txtTitle.setText(selectedCategory);
            }
        }

        // ==========================================
        // SEARCH INPUT LISTENER
        // ==========================================

        if (edtProductListSearch != null) {
            edtProductListSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String query = s.toString();
                    if (btnClearProductSearch != null) {
                        btnClearProductSearch.setVisibility(!query.isEmpty() ? View.VISIBLE : View.GONE);
                    }
                    performSearch(query);
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        if (btnClearProductSearch != null) {
            btnClearProductSearch.setOnClickListener(v -> {
                if (edtProductListSearch != null) {
                    edtProductListSearch.setText("");
                }
            });
        }

        // If pre-filled query was provided from MainActivity or CategoryActivity
        if (initialSearchQuery != null && !initialSearchQuery.trim().isEmpty() && edtProductListSearch != null) {
            edtProductListSearch.setText(initialSearchQuery.trim());
            edtProductListSearch.setSelection(initialSearchQuery.trim().length());
        } else {
            performSearch("");
        }

        // ==========================================
        // CART BUTTON
        // ==========================================

        Button btnCart = findViewById(R.id.btnProductListCart);

        if (btnCart != null) {
            ViewCompat.setOnApplyWindowInsetsListener(btnCart, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                if (lp != null) {
                    int baseMargin = getResources().getDimensionPixelSize(R.dimen.button_margin_bottom);
                    lp.bottomMargin = systemBars.bottom + baseMargin;
                    v.setLayoutParams(lp);
                }
                return insets;
            });

            btnCart.setOnClickListener(v -> {
                Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }
    }

    private void performSearch(String query) {
        ArrayList<Product> products = ProductData.searchProducts(query, selectedCategory);
        displayProducts(products, query);
    }

    // ==========================================
    // DISPLAY PRODUCTS
    // ==========================================

    private void displayProducts(ArrayList<Product> products, String currentQuery) {

        productContainer.removeAllViews();

        if (products.isEmpty()) {
            TextView emptyText = new TextView(this);
            if (currentQuery != null && !currentQuery.trim().isEmpty()) {
                emptyText.setText("No products found matching \"" + currentQuery.trim() + "\".");
            } else {
                emptyText.setText("No products available in this category.");
            }
            emptyText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.body_text_size));
            emptyText.setTextColor(getColor(R.color.text_secondary));
            emptyText.setPadding(16, 48, 16, 48);
            emptyText.setGravity(android.view.Gravity.CENTER);
            productContainer.addView(emptyText);
        } else {
            for (Product product : products) {
                addProductToScreen(product);
            }
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
        card.setBackgroundResource(R.drawable.bg_product_card);

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
