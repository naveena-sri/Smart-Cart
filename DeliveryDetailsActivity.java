package com.example.bsn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class DeliveryDetailsActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_REQUEST = 101;

    private EditText fullName, contactNumber, streetAddress, city, zipCode;
    private Button submitForm;
    private String adminPhoneNumber = "+919080538829";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        fullName = findViewById(R.id.fullName);
        contactNumber = findViewById(R.id.contactNumber);
        streetAddress = findViewById(R.id.streetAddress);
        city = findViewById(R.id.city);
        zipCode = findViewById(R.id.zipCode);
        submitForm = findViewById(R.id.submitForm);

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    int totalAmount = calculateTotalAmount(); // Calculate the price

                    Intent intent = new Intent(DeliveryDetailsActivity.this, PaymentActivity.class);
                    intent.putExtra("TOTAL_PRICE", String.valueOf(totalAmount));  // Pass total price
                    intent.putExtra("USER_NAME", fullName.getText().toString());
                    intent.putExtra("USER_PHONE", contactNumber.getText().toString());
                    intent.putExtra("USER_ADDRESS", streetAddress.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(DeliveryDetailsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private int calculateTotalAmount() {
        int totalAmount = 0;
        List<CartItem> cartList = ProductDetailActivity.cartList;

        for (CartItem item : cartList) {
            totalAmount += item.getPrice();
        }
        return totalAmount;
    }


    private boolean validateForm() {
        return !fullName.getText().toString().isEmpty()
                && !contactNumber.getText().toString().isEmpty()
                && !streetAddress.getText().toString().isEmpty()
                && !city.getText().toString().isEmpty()
                && !zipCode.getText().toString().isEmpty();
    }

    private void sendOrderSMS() {
        String name = fullName.getText().toString();
        String phone = contactNumber.getText().toString();
        String address = streetAddress.getText().toString() + ", " + city.getText().toString() + ", " + zipCode.getText().toString();

        List<CartItem> cartList = ProductDetailActivity.cartList;
        StringBuilder orderDetails = new StringBuilder();
        int totalAmount = 0;

        orderDetails.append("Name: ").append(name)
                .append("\nPhone: ").append(phone)
                .append("\nAddress: ").append(address)
                .append("\n\nProducts Ordered:\n");

        for (CartItem item : cartList) {
            int totalPrice = item.getPrice();
            totalAmount += totalPrice;
            orderDetails.append(item.getProductName()).append(" - ₹").append(item.getPrice()).append("\n");
        }

        orderDetails.append("\nTotal Amount: ₹").append(totalAmount);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(adminPhoneNumber, null, orderDetails.toString(), null, null);
            Toast.makeText(this, "Order details are sent!", Toast.LENGTH_LONG).show();

            // Proceed to PaymentActivity
            Intent intent = new Intent(DeliveryDetailsActivity.this, OrderConfirmationActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOrderSMS();
            } else {
                Toast.makeText(this, "SMS permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
