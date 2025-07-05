package com.example.bsn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private String totalPrice, userName, userPhone, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Get data from intent
        totalPrice = getIntent().getStringExtra("TOTAL_PRICE");
        if (totalPrice != null && !totalPrice.isEmpty()) {
            totalPrice = totalPrice.replaceAll("[^0-9]", "");
        } else {
            totalPrice = "0";  // Fallback to 0 if price is missing
        }

        userName = getIntent().getStringExtra("USER_NAME");
        userPhone = getIntent().getStringExtra("USER_PHONE");
        userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Start payment
        startPayment();
    }

    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("Q4ARL5JowdQFT5");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "BSN Supermarket");
            options.put("description", "Order Payment");
            options.put("currency", "INR");
            options.put("amount", Integer.parseInt(totalPrice) * 100);  // Convert to paise

            JSONObject prefill = new JSONObject();
            prefill.put("email", userEmail);
            prefill.put("contact", userPhone);
            options.put("prefill", prefill);

            checkout.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_LONG).show();

        // Show the bill after payment
        Intent intent = new Intent(PaymentActivity.this, BillActivity.class);
        intent.putExtra("TOTAL_PRICE", totalPrice);
        startActivity(intent);
        finish();
    }


    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_LONG).show();
        finish();
    }
}
