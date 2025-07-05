package com.example.bsn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        ImageView orderConfirmedImage = findViewById(R.id.orderConfirmedImage);
        orderConfirmedImage.setImageResource(R.drawable.order_confirmed);

        // Delay for 3 seconds and go back to the main activity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(OrderConfirmationActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
