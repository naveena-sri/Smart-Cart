package com.example.bsn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productDescription, productPrice;
    private RadioGroup weightGroup;
    private Button addToCartButton, viewCartButton;
    private int selectedWeight = 1;
    private int basePrice;
    static List<CartItem> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Initialize views
        productImage = findViewById(R.id.prodimage);
        productDescription = findViewById(R.id.prodDescription);
        productPrice = findViewById(R.id.prodPrice);
        weightGroup = findViewById(R.id.wGroup);
        addToCartButton = findViewById(R.id.addTCartButton);
        viewCartButton = findViewById(R.id.CartButton);

        // Get product details from intent
        String name = getIntent().getStringExtra("PRODUCT_NAME");
        int image = getIntent().getIntExtra("PRODUCT_IMAGE", 0);
        basePrice = Integer.parseInt(getIntent().getStringExtra("PRODUCT_PRICE").replace("₹", ""));
        String productUnit = getIntent().getStringExtra("PRODUCT_UNIT");

        productImage.setImageResource(image);
        productDescription.setText(name);

        // Update price
        updatePrice();

        // Dynamically change unit in radio buttons
        setRadioGroupUnits(name,productUnit);

        // Handle weight selection
        weightGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.w1kg) selectedWeight = 1;
            else if (checkedId == R.id.w2kg) selectedWeight = 2;
            else if (checkedId == R.id.w3kg) selectedWeight = 5;

            updatePrice();
            addToCartButton.setText("Add to Cart");
            addToCartButton.setEnabled(true);
        });

        // Handle add to cart
        addToCartButton.setOnClickListener(v -> {
            int totalPrice = basePrice * selectedWeight;
            cartList.add(new CartItem(name, selectedWeight, productUnit, totalPrice, image));
            addToCartButton.setText("Added to Cart");
            addToCartButton.setEnabled(false);
        });

        // View cart
        viewCartButton.setOnClickListener(v -> startActivity(new Intent(ProductDetailActivity.this, CartActivity.class)));
    }

    private void updatePrice() {
        int totalPrice = basePrice * selectedWeight;
        productPrice.setText("Price: ₹" + totalPrice);
    }

    private void setRadioGroupUnits(String productName, String unit) {
        if (unit == null || unit.isEmpty()) unit = "kg"; // Default to kg if missing

        switch (unit) {
            case "ml":
                if (productName.toLowerCase().contains("honey")) {
                    setRadioButtonText(50, 100, 200, unit); // Honey starts from 50ml
                } else {
                    setRadioButtonText(250, 500, 1000, unit); // Oils start from 250ml
                }
                break;

            case "g":
                if (productName.toLowerCase().contains("tea") || productName.toLowerCase().contains("powder")) {
                    setRadioButtonText(100, 250, 500, unit); // Tea, powders start from 100g
                } else {
                    setRadioButtonText(250, 500, 1000, unit); // Default for other items
                }
                break;

            case "kg":
                setRadioButtonText(1, 2, 5, unit); // Rice and cereals in kg
                break;

            case "units":
                setRadioButtonText(1, 2, 3, unit); // Soaps, shampoo, etc.
                break;

            default:
                setRadioButtonText(1, 2, 3, unit); // Fallback case
                break;
        }
    }
    private void setRadioButtonText(int option1, int option2, int option3, String unit) {
        ((RadioButton) findViewById(R.id.w1kg)).setText(option1 + " " + unit);
        ((RadioButton) findViewById(R.id.w2kg)).setText(option2 + " " + unit);
        ((RadioButton) findViewById(R.id.w3kg)).setText(option3 + " " + unit);
    }


}
