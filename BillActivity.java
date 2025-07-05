package com.example.bsn;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class BillActivity extends AppCompatActivity {

    private TextView billText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        billText = findViewById(R.id.billText);

        // Get data from intent
        List<CartItem> cartList = ProductDetailActivity.cartList;
        String totalPrice = getIntent().getStringExtra("TOTAL_PRICE");

        if (totalPrice == null || totalPrice.isEmpty()) {
            totalPrice = "0";
        }

        // Generate and display the bill
        String bill = generateBill(cartList, totalPrice);
        billText.setText(bill);
    }

    private String generateBill(List<CartItem> cartList, String totalPrice) {
        StringBuilder bill = new StringBuilder();

        // Add header
        bill.append("      BSN Supermarket\n");
        bill.append("         Receipt\n");
        bill.append("     --------------------\n\n");

        // Add product details
        bill.append(String.format("%-20s %-8s %-10s\n", "Product", "Qty", "Price"));
        bill.append("-----------------------------------------\n");

        if (cartList != null && !cartList.isEmpty()) {
            for (CartItem item : cartList) {
                String productName = item.getProductName();
                int quantity = item.getWeight();
                int price = item.getPrice();

                bill.append(String.format("%-20s %-8d ₹%-10d\n", productName, quantity, price));
            }
        } else {
            bill.append("No items in cart\n");
        }

        // Add footer with total price
        bill.append("-----------------------------------------\n");
        bill.append(String.format("%-20s %-8s ₹%-10s\n", "Total Price:", "", totalPrice));
        bill.append("\nThank you for shopping with us!\n");

        return bill.toString();
    }
}
