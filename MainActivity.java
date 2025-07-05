package com.example.bsn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bsn.CategoryActivity;
import com.example.bsn.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example for Rice category
        ImageView imageRice = findViewById(R.id.image_rice);
        imageRice.setOnClickListener(view -> openCategory("Rice"));

        // Example for Cooking Essentials
        ImageView imageCooking = findViewById(R.id.image_cooking_essentials);
        imageCooking.setOnClickListener(view -> openCategory("Cooking Essentials"));

        // Add click listeners for all categories
    }

    private void openCategory(String categoryName) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("CATEGORY_NAME", categoryName);
        startActivity(intent);
    }
}
