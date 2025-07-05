package com.example.bsn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private TextView totalPriceText;
    private Button buyNowButton;
    private static List<CartItem> cartList = ProductDetailActivity.cartList;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView cartListView = findViewById(R.id.cartLiView);
        totalPriceText = findViewById(R.id.totPrice);
        buyNowButton = findViewById(R.id.buyNow);


        adapter = new CartAdapter(this, cartList, totalPriceText);
        cartListView.setAdapter(adapter);

        updateTotalPrice();


        buyNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, DeliveryDetailsActivity.class);
            startActivity(intent);
        });


    }

    private void updateTotalPrice() {
        int total = 0;
        for (CartItem item : cartList) total += item.getPrice();
        totalPriceText.setText("Total Price: ₹" + total);
    }


}
