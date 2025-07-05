package com.example.bsn;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {
    private List<Product> subCategoryProducts = new ArrayList<>();
    private List<Product> filteredProducts = new ArrayList<>();
    private ProductAdapter adapter;
    private String categoryName;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        GridView gridView = findViewById(R.id.grid_sub_products);
        searchView = findViewById(R.id.search);

        categoryName = getIntent().getStringExtra("CATEGORY_NAME");

        loadProductsForCategory(categoryName);
        filteredProducts.addAll(subCategoryProducts);

        adapter = new ProductAdapter(this, filteredProducts);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = filteredProducts.get(position);
            Intent intent = new Intent(SubCategoryActivity.this, ProductDetailActivity.class);
            intent.putExtra("PRODUCT_NAME", selectedProduct.getName());
            intent.putExtra("PRODUCT_PRICE", selectedProduct.getPrice());
            intent.putExtra("PRODUCT_WEIGHT", selectedProduct.getWeight());
            intent.putExtra("PRODUCT_UNIT", getProductUnit(selectedProduct.getName()));
            intent.putExtra("PRODUCT_IMAGE", selectedProduct.getImageResId());
            startActivity(intent);
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });

    }
    private String getProductUnit(String productName) {
        switch (productName.toLowerCase()) {

            // 🟢 Rice (kg)
            case "basmati rice":
            case "seeraga sambha rice":
            case "royal mayil(nei kicchadi)":
            case "sultan jeera rice":
            case "black rice":
            case "maharaja bullet rice":
            case "kurunai rice":
            case "paccharisi":
            case "ponni rice":
            case "india gate basmati":
                return "kg";

            // 🟡 Cooking Essentials (ml or g)
            case "gold winner oil":
            case "grb ghee":
            case "lion honey":
            case "lamp oil":
                return "ml";

            case "chakra gold tea powder":
            case "bru coffee powder":
            case "aachi turmeric powder":
            case "aachi chilli powder":
            case "naga rava":
                return "g";

            case "lion dates":
            case "maggi noodles":
                return "units";

            // 🟠 Biscuits and Snacks (g)
            case "good day":
            case "marie gold":
            case "dairy milk":
            case "kitkat":
            case "kurkure":
            case "lays":
            case "lollipop":
            case "peanut burfi":
            case "grinded peanut burfi":
            case "7up":
                return "g";

            // 🟣 Soaps & Shampoo (units)
            case "hamam":
            case "mysore sandal":
            case "meera shampoo":
            case "dove shampoo":
            case "cinthol soap":
            case "fogg perfume":
            case "ponds powder":
            case "gokul santol powder":
            case "himalaya powder":
            case "johnson baby cream":
                return "units";

            // 🔴 Divine Products (g or units)
            case "camphor":
            case "agarbathi":
            case "thiruneeru":
            case "kungumam":
            case "sambrani":
            case "pachai karpoor":
                return "g";

            case "thiri":
                return "units";

            // 🟤 Cereals (kg or g)
            case "red kidney bean(rajma)":
            case "chick peas(kadalai paruppu)":
            case "cow peas(thatta payaru)":
            case "moond dal(paasi paruppu)":
            case "white channa":
            case "dhole dal(thuvaram paruppu)":
            case "green gram(paasi payaru)":
            case "urad bean(ulundu)":
            case "black channa":
                return "kg";

            case "fried peanuts":
                return "g";

            // 🟠 Default fallback
            default:
                return "kg"; // Use kg as the default
        }
    }

    private void filterProducts(String query) {
        filteredProducts.clear();
        if (query.isEmpty()) {
            filteredProducts.addAll(subCategoryProducts);
        } else {
            for (Product product : subCategoryProducts) {
                if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredProducts.add(product);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void loadProductsForCategory(String category) {
        subCategoryProducts.clear();

        switch (category) {
            case "Rice":
                subCategoryProducts.add(new Product("Basmati Rice", "₹115", "5kg", R.drawable.rice_item_1));
                subCategoryProducts.add(new Product("Seeraga sambha rice", "₹105", "5kg", R.drawable.rice_image_2));
                subCategoryProducts.add(new Product("Royal mayil(Nei kicchadi)", "₹65", "5kg", R.drawable.rice_image_3));
                subCategoryProducts.add(new Product("Sultan jeera rice", "₹65", "5kg", R.drawable.rice_image_4));
                subCategoryProducts.add(new Product("Black rice", "₹290", "5kg", R.drawable.rice_image_5));
                subCategoryProducts.add(new Product("Maharaja bullet rice", "₹66", "5kg", R.drawable.rice_image_6));
                subCategoryProducts.add(new Product("Kurunai Rice", "₹250", "5kg", R.drawable.rice_image_7));
                subCategoryProducts.add(new Product("Paccharisi", "₹250", "5kg", R.drawable.rice_image_8));
                subCategoryProducts.add(new Product("Ponni rice", "₹250", "5kg", R.drawable.rice_image_9));
                subCategoryProducts.add(new Product("India gate basmati", "₹250", "5kg", R.drawable.rice_image_10));
                break;
            case "Cooking Essentials":
                subCategoryProducts.add(new Product("gold winner Oil", "₹150", "1L", R.drawable.grocery_item_1));
                subCategoryProducts.add(new Product("GRB ghee", "₹500", "1L", R.drawable.grocery_item_2));
                subCategoryProducts.add(new Product("Chakra gold tea powder", "₹200", "1L", R.drawable.grocery_item_3));
                subCategoryProducts.add(new Product("Bru coffee powder", "₹200", "1L", R.drawable.grocery_item_4));
                subCategoryProducts.add(new Product("Lion honey", "₹200", "1L", R.drawable.grocery_item_5));
                subCategoryProducts.add(new Product("Lion dates", "₹200", "1L", R.drawable.grocery_item_6));
                subCategoryProducts.add(new Product("Maggi noodles", "₹200", "1L", R.drawable.grocery_item_7));
                subCategoryProducts.add(new Product("Aachi turmeric powder", "₹200", "1L", R.drawable.grocery_item_8));
                subCategoryProducts.add(new Product("Aachi chilli powder", "₹200", "1L", R.drawable.grocery_item_9));
                subCategoryProducts.add(new Product("Naga rava", "₹200", "1L", R.drawable.grocery_item_10));
                break;
            case "Biscuits":
                subCategoryProducts.add(new Product("Good day", "₹50", "300g", R.drawable.biscuit_item_1));
                subCategoryProducts.add(new Product("Marie gold", "₹40", "250g", R.drawable.biscuit_item_2));
                subCategoryProducts.add(new Product("Dairy milk", "₹45", "200g", R.drawable.biscuit_item_3));
                subCategoryProducts.add(new Product("Kitkat", "₹45", "200g", R.drawable.biscuit_item_4));
                subCategoryProducts.add(new Product("Kurkure", "₹45", "200g", R.drawable.biscuit_item_5));
                subCategoryProducts.add(new Product("Lays", "₹45", "200g", R.drawable.biscuit_item_6));
                subCategoryProducts.add(new Product("Lollipop", "₹45", "200g", R.drawable.biscuit_item_7));
                subCategoryProducts.add(new Product("Peanut burfi", "₹45", "200g", R.drawable.biscuit_item_8));
                subCategoryProducts.add(new Product("Grinded peanut burfi", "₹45", "200g", R.drawable.biscuit_item_9));
                subCategoryProducts.add(new Product("7up", "₹45", "200g", R.drawable.biscuit_item_10));
                break;
            case "Soaps & Shampoo":
                subCategoryProducts.add(new Product("Hamam", "₹50", "300g", R.drawable.soap_item_1));
                subCategoryProducts.add(new Product("Mysore sandal", "₹50", "300g", R.drawable.soap_item_2));
                subCategoryProducts.add(new Product("Meera shampoo", "₹50", "300g", R.drawable.soap_item_3));
                subCategoryProducts.add(new Product("Dove shampoo", "₹50", "300g", R.drawable.soap_item_4));
                subCategoryProducts.add(new Product("Cinthol soap", "₹50", "300g", R.drawable.soap_item_5));
                subCategoryProducts.add(new Product("Fogg perfume", "₹50", "300g", R.drawable.soap_item_6));
                subCategoryProducts.add(new Product("Ponds powder", "₹50", "300g", R.drawable.soap_item_7));
                subCategoryProducts.add(new Product("Gokul santol powder", "₹50", "300g", R.drawable.soap_item_8));
                subCategoryProducts.add(new Product("Himalaya powder", "₹50", "300g", R.drawable.soap_item_9));
                subCategoryProducts.add(new Product("johnson baby cream", "₹50", "300g", R.drawable.soap_item_10));
                break;

            case "Divine Products":
                subCategoryProducts.add(new Product("Camphor", "₹50", "300g", R.drawable.divine_1));
                subCategoryProducts.add(new Product("Agarbathi", "₹50", "300g", R.drawable.divine_2));
                subCategoryProducts.add(new Product("Thiruneeru", "₹50", "300g", R.drawable.divine_3));
                subCategoryProducts.add(new Product("Kungumam", "₹50", "300g", R.drawable.divine_4));
                subCategoryProducts.add(new Product("sambrani", "₹50", "300g", R.drawable.divine_5));
                subCategoryProducts.add(new Product("Pachai karpoor", "₹50", "300g", R.drawable.divine_6));
                subCategoryProducts.add(new Product("Lamp oil", "₹50", "300g", R.drawable.divine_7));
                subCategoryProducts.add(new Product("Thiri", "₹50", "300g", R.drawable.divine_8));
                break;


            case "Cereals":
                subCategoryProducts.add(new Product("Red kidney bean(Rajma)", "₹50", "300g", R.drawable.cerel_1));
                subCategoryProducts.add(new Product("Fried peanuts", "₹50", "300g", R.drawable.cerel_2));
                subCategoryProducts.add(new Product("Chick peas(kadalai paruppu)", "₹50", "300g", R.drawable.cerel_3));
                subCategoryProducts.add(new Product("Cow peas(thatta payaru)", "₹50", "300g", R.drawable.cerel_4));
                subCategoryProducts.add(new Product("Moong dal(paasi paruppu)", "₹50", "300g", R.drawable.cerel_5));
                subCategoryProducts.add(new Product("white channa", "₹50", "300g", R.drawable.cerel_6));
                subCategoryProducts.add(new Product("Dhole dal(thuvaram paruppu)", "₹50", "300g", R.drawable.cerel_7));
                subCategoryProducts.add(new Product("green gram(Paasi payaru)", "₹50", "300g", R.drawable.cerel_8));
                subCategoryProducts.add(new Product("urad bean(Ulundu)", "₹50", "300g", R.drawable.cerel_9));
                subCategoryProducts.add(new Product("Black channa", "₹50", "300g", R.drawable.cerel_10));
                break;

            default:
                //subCategoryProducts.add(new Product("Generic Product 1", "₹100", "500g", R.drawable.default_image));
                //subCategoryProducts.add(new Product("Generic Product 2", "₹150", "1kg", R.drawable.default_image));
                //subCategoryProducts.add(new Product("Generic Product 3", "₹200", "750g", R.drawable.default_image));
                break;
        }
    }
}
