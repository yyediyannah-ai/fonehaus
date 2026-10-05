package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView txtOrderDetails = findViewById(R.id.txtOrderDetails);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        String orderSummary = getIntent().getStringExtra("orderSummary");

        if (txtOrderDetails != null && orderSummary != null) {
            txtOrderDetails.setText(orderSummary);
        }

        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(OrderConfirmationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
