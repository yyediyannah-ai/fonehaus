package com.fonehaus.app;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fonehaus.app.model.Cart;
import com.fonehaus.app.model.CartManager;
import com.fonehaus.app.model.Product;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);

        cartContainer =
                findViewById(R.id.cartContainer);

        displayCart();
    }

    // ==========================================
    // DISPLAY CART
    // ==========================================

    private void displayCart() {

        Cart cart =
                CartManager.getCart();

        cartContainer.removeAllViews();

        if (cart.getProducts().isEmpty()) {

            TextView emptyMessage =
                    new TextView(this);

            emptyMessage.setText(
                    "Your shopping cart is empty."
            );

            emptyMessage.setTextSize(18);

            emptyMessage.setTextColor(
                    getColor(R.color.white)
            );

            cartContainer.addView(emptyMessage);

            return;
        }

        for (Product product :
                cart.getProducts()) {

            TextView productText =
                    new TextView(this);

            productText.setText(
                    product.getProductName()
                            + "\nK"
                            + String.format(
                            "%.2f",
                            product.getPrice())
            );

            productText.setTextSize(18);

            productText.setTextColor(
                    getColor(R.color.white)
            );

            productText.setPadding(
                    0, 20, 0, 20
            );

            cartContainer.addView(productText);
        }

        // ======================================
        // TOTAL
        // ======================================

        TextView totalText =
                new TextView(this);

        totalText.setText(
                "TOTAL: K"
                        + String.format(
                        "%.2f",
                        cart.calculateTotal())
        );

        totalText.setTextSize(24);

        totalText.setTextColor(
                getColor(R.color.fone_yellow)
        );

        totalText.setPadding(
                0, 30, 0, 30
        );

        cartContainer.addView(totalText);
    }
}
