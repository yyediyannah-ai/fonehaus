package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fonehaus.app.model.Cart;
import com.fonehaus.app.model.CartManager;
import com.fonehaus.app.model.Customer;
import com.fonehaus.app.model.Order;
import com.fonehaus.app.model.Product;

import java.util.Locale;
import java.util.UUID;

public class CheckoutActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtAddress;
    private RadioGroup rgPayment;
    private TextView txtSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        edtName = findViewById(R.id.edtCustomerName);
        edtPhone = findViewById(R.id.edtCustomerPhone);
        edtEmail = findViewById(R.id.edtCustomerEmail);
        edtAddress = findViewById(R.id.edtDeliveryAddress);
        rgPayment = findViewById(R.id.rgPaymentMethod);
        txtSummary = findViewById(R.id.txtCheckoutSummary);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        displaySummary();

        btnPlaceOrder.setOnClickListener(v -> processCheckout());
    }

    private void displaySummary() {
        Cart cart = CartManager.getCart();

        if (cart.getProducts().isEmpty()) {
            txtSummary.setText("Your cart is empty.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Items in Cart: ").append(cart.getItemCount()).append("\n\n");

        for (Product product : cart.getProducts()) {
            // Polymorphic method call
            sb.append("• ").append(product.displayProduct()).append("\n");
        }

        sb.append("\nTotal: K").append(String.format(Locale.US, "%.2f", cart.calculateTotal()));
        txtSummary.setText(sb.toString());
    }

    private void processCheckout() {
        Cart cart = CartManager.getCart();

        if (cart.getProducts().isEmpty()) {
            Toast.makeText(this, "Your cart is empty. Please add products first.", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = edtName.getText() != null ? edtName.getText().toString().trim() : "";
        String phone = edtPhone.getText() != null ? edtPhone.getText().toString().trim() : "";
        String email = edtEmail.getText() != null ? edtEmail.getText().toString().trim() : "";
        String address = edtAddress.getText() != null ? edtAddress.getText().toString().trim() : "";

        if (name.isEmpty()) {
            edtName.setError("Please enter your name");
            edtName.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            edtPhone.setError("Please enter your phone number");
            edtPhone.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            edtAddress.setError("Please enter delivery address");
            edtAddress.requestFocus();
            return;
        }

        // Selected payment method
        int selectedId = rgPayment.getCheckedRadioButtonId();
        RadioButton selectedRb = findViewById(selectedId);
        String paymentMethod = selectedRb != null ? selectedRb.getText().toString() : "Cash on Delivery";

        // Create Customer & Order
        Customer customer = new Customer(name, phone, email.isEmpty() ? "N/A" : email, address);
        String orderId = "FH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.US);

        Order order = new Order(orderId, customer, cart, paymentMethod);

        // Clear cart after placing order
        cart.clearCart();

        // Pass order details to Confirmation screen
        Intent intent = new Intent(CheckoutActivity.this, OrderConfirmationActivity.class);
        intent.putExtra("orderSummary", order.getOrderSummary());
        startActivity(intent);
        finish();
    }
}
