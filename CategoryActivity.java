package com.example.bsn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private List<Product> productList = new ArrayList<>();
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        GridView gridView = findViewById(R.id.grid_products);

        // Load Categories
        loadCategories();

        // Set Adapter
        adapter = new ProductAdapter(this, productList);
        gridView.setAdapter(adapter);

        // Handle Category Click
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedCategory = productList.get(position);
            Intent intent = new Intent(CategoryActivity.this, SubCategoryActivity.class);
            intent.putExtra("CATEGORY_NAME", selectedCategory.getName());
            startActivity(intent);
        });
    }

    private void loadCategories() {
        productList.clear();
        productList.add(new Product("Rice", "₹250", "5kg", R.drawable.rice_image));
        productList.add(new Product("Cooking Essentials", "₹500", "1L", R.drawable.cooking_essentials_image));
        productList.add(new Product("Biscuits", "₹100", "500g", R.drawable.biscuits));
        productList.add(new Product("Soaps & Shampoo", "₹150", "1 unit", R.drawable.soap_shampoos));
        productList.add(new Product("Cereals", "₹200", "2kg", R.drawable.cerels));
        productList.add(new Product("Divine Products", "₹350", "500g", R.drawable.divine_products));
    }
}
