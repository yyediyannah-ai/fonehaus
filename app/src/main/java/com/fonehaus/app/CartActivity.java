package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fonehaus.app.model.Cart;
import com.fonehaus.app.model.CartManager;
import com.fonehaus.app.model.Product;
import com.google.android.material.button.MaterialButton;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);

        cartContainer = findViewById(R.id.cartContainer);
        Button btnCheckout = findViewById(R.id.btnCheckout);

        if (btnCheckout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(btnCheckout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                if (lp != null) {
                    int baseMargin = getResources().getDimensionPixelSize(R.dimen.button_margin_bottom);
                    lp.bottomMargin = systemBars.bottom + baseMargin;
                    v.setLayoutParams(lp);
                }
                return insets;
            });

            btnCheckout.setOnClickListener(v -> {
                Cart cart = CartManager.getCart();
                if (cart.getProducts().isEmpty()) {
                    Toast.makeText(CartActivity.this, "Your cart is empty. Add products before checkout.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                    startActivity(intent);
                }
            });
        }

        displayCart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayCart();
    }

    // ==========================================
    // DISPLAY CART
    // ==========================================

    private void displayCart() {

        Cart cart = CartManager.getCart();

        cartContainer.removeAllViews();

        if (cart.getProducts().isEmpty()) {

            TextView emptyMessage = new TextView(this);
            emptyMessage.setText("Your shopping cart is empty.");
            emptyMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_text_size));
            emptyMessage.setTextColor(getColor(R.color.text_secondary));
            emptyMessage.setGravity(Gravity.CENTER);
            emptyMessage.setPadding(16, 48, 16, 48);

            cartContainer.addView(emptyMessage);
            return;
        }

        int cardPadding = getResources().getDimensionPixelSize(R.dimen.card_padding);
        int cornerRadius = getResources().getDimensionPixelSize(R.dimen.button_corner_radius);

        for (Product product : cart.getProducts()) {

            LinearLayout itemCard = new LinearLayout(this);
            itemCard.setOrientation(LinearLayout.VERTICAL);
            itemCard.setBackgroundResource(R.drawable.bg_product_card);
            itemCard.setPadding(cardPadding, cardPadding, cardPadding, cardPadding);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            lp.setMargins(0, 0, 0, cardPadding);
            itemCard.setLayoutParams(lp);

            // Title / Polymorphic display
            TextView productText = new TextView(this);
            productText.setText(product.displayProduct());
            productText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_text_size));
            productText.setTypeface(null, android.graphics.Typeface.BOLD);
            productText.setTextColor(getColor(R.color.text_primary));
            itemCard.addView(productText);

            // Category Details
            TextView detailsText = new TextView(this);
            detailsText.setText(product.getCategoryDetails());
            detailsText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.chip_text_size));
            detailsText.setTextColor(getColor(R.color.text_secondary));
            detailsText.setPadding(0, 4, 0, 12);
            itemCard.addView(detailsText);

            // Remove Button
            MaterialButton btnRemove = new MaterialButton(this);
            btnRemove.setText("Remove Item");
            btnRemove.setAllCaps(false);
            btnRemove.setTextColor(getColor(R.color.black));
            btnRemove.setBackgroundTintList(getColorStateList(R.color.fone_yellow_light));
            btnRemove.setStrokeColor(getColorStateList(R.color.fone_yellow_dark));
            btnRemove.setStrokeWidth(1);
            btnRemove.setCornerRadius(cornerRadius);

            btnRemove.setOnClickListener(v -> {
                cart.removeProduct(product);
                displayCart();
            });

            itemCard.addView(btnRemove);

            cartContainer.addView(itemCard);
        }

        // ======================================
        // TOTAL CARD
        // ======================================

        LinearLayout totalCard = new LinearLayout(this);
        totalCard.setOrientation(LinearLayout.VERTICAL);
        totalCard.setBackgroundResource(R.drawable.bg_product_card);
        totalCard.setPadding(cardPadding, cardPadding, cardPadding, cardPadding);

        TextView totalText = new TextView(this);
        totalText.setText("TOTAL: K" + String.format("%.2f", cart.calculateTotal()));
        totalText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_text_size));
        totalText.setTypeface(null, android.graphics.Typeface.BOLD);
        totalText.setTextColor(getColor(R.color.fone_yellow_text));
        totalText.setGravity(Gravity.CENTER);

        totalCard.addView(totalText);
        cartContainer.addView(totalCard);
    }
}
