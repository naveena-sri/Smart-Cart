package com.example.bsn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private List<String> productList;
    private ArrayAdapter<String> adapter;
    private ListView listViewProducts;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);

        ListView listView = findViewById(R.id.listViewProducts);
        Button preOrderButton = findViewById(R.id.preorderbutton);

        listViewProducts = findViewById(R.id.listViewProducts);
        searchView = findViewById(R.id.searchView);
        loadProductList();

        TextView locationText = findViewById(R.id.locationText);

        locationText.setOnClickListener(v -> {
            String locationUrl = "https://maps.app.goo.gl/F7bVJ4dopqA22P1J7"; // Use your exact link
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl));
            startActivity(intent);
        });


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        listViewProducts.setAdapter(adapter);
        ImageView recommendedProduct1 = findViewById(R.id.recommend_image1);
        recommendedProduct1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SubCategoryActivity.class);
            intent.putExtra("CATEGORY_NAME", "Rice");
            startActivity(intent);
        });

        ImageView recommendedProduct2 = findViewById(R.id.recommend_image2);
        recommendedProduct2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SubCategoryActivity.class);
            intent.putExtra("CATEGORY_NAME", "Cooking Essentials");
            startActivity(intent);
        });

        ImageView recommendedProduct3 = findViewById(R.id.recommend_image3);
        recommendedProduct3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SubCategoryActivity.class);
            intent.putExtra("CATEGORY_NAME", "Biscuits");
            startActivity(intent);
        });

        ImageView recommendedProduct4 = findViewById(R.id.recommend_image4);
        recommendedProduct4.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SubCategoryActivity.class);
            intent.putExtra("CATEGORY_NAME", "Soaps & Shampoo");
            startActivity(intent);
        });

        ImageView recommendedProduct5 = findViewById(R.id.recommend_image5);
        recommendedProduct5.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SubCategoryActivity.class);
            intent.putExtra("CATEGORY_NAME", "Cereals");
            startActivity(intent);
        });

        ImageView recommendedProduct6 = findViewById(R.id.recommend_image6);
        recommendedProduct6.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SubCategoryActivity.class);
            intent.putExtra("CATEGORY_NAME", "Divine Products");
            startActivity(intent);
        });



        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                listViewProducts.setVisibility(View.VISIBLE);
            } else {
                listViewProducts.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(() -> {
            listViewProducts.setVisibility(View.GONE);
            return false;
        });

        // Handle search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                navigateToSubCategory(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });


        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapter.getItem(position);
                navigateToSubCategory(selectedItem);
            }
        });


        ImageView categoryIcon = findViewById(R.id.cateicon);
        ImageView cartIcon = findViewById(R.id.cart_icon);
        Button contributorButton = findViewById(R.id.contbutton);
        Button suggestionButton = findViewById(R.id.suggbutton);


        categoryIcon.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, CategoryActivity.class)));

        cartIcon.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, CartActivity.class)));

        suggestionButton.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, FeedbackActivity.class)));

        contributorButton.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ContributorActivity.class)));


    }
    private void loadProductList() {
        productList = new ArrayList<>();

        // Add categories
        productList.add("Rice");
        productList.add("Cooking Essentials");
        productList.add("Biscuits");
        productList.add("Soaps & Shampoo");
        productList.add("Cereals");
        productList.add("Divine Products");

        // Add specific products (you can add more!)
        productList.add("Basmati Rice");
        productList.add("Seeraga Samba Rice");
        productList.add("Good Day Biscuits");
        productList.add("Dove Shampoo");
        productList.add("Gold Winner Oil");
    }
    private void navigateToSubCategory(String query) {
        Intent intent = new Intent(HomeActivity.this, SubCategoryActivity.class);

        switch (query.toLowerCase()) {
            case "rice":
                intent.putExtra("CATEGORY_NAME", "Rice");
                break;
            case "cooking essentials":
                intent.putExtra("CATEGORY_NAME", "Cooking Essentials");
                break;
            case "biscuits":
                intent.putExtra("CATEGORY_NAME", "Biscuits");
                break;
            case "soaps & shampoo":
                intent.putExtra("CATEGORY_NAME", "Soaps & Shampoo");
                break;
            case "cereals":
                intent.putExtra("CATEGORY_NAME", "Cereals");
                break;
            case "divine products":
                intent.putExtra("CATEGORY_NAME", "Divine Products");
                break;
            default:
                return; // Do nothing if the category isn't found
        }

        startActivity(intent);
    }



}