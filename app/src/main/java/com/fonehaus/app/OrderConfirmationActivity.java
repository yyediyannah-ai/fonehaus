package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView txtOrderDetails = findViewById(R.id.txtOrderDetails);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        if (btnBackToHome != null) {
            ViewCompat.setOnApplyWindowInsetsListener(btnBackToHome, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                if (lp != null) {
                    int baseMargin = getResources().getDimensionPixelSize(R.dimen.button_margin_bottom);
                    lp.bottomMargin = systemBars.bottom + baseMargin;
                    v.setLayoutParams(lp);
                }
                return insets;
            });

            btnBackToHome.setOnClickListener(v -> {
                Intent intent = new Intent(OrderConfirmationActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        String orderSummary = getIntent().getStringExtra("orderSummary");

        if (txtOrderDetails != null && orderSummary != null) {
            txtOrderDetails.setText(orderSummary);
        }
    }
}
