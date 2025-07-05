package com.example.bsn;

public class CartItem {
    private String productName;
    private int weight;
    private String unit;   // Add a unit field
    private int price;
    private int imageResId;

    public CartItem(String productName, int weight, String unit, int price, int imageResId) {
        this.productName = productName;
        this.weight = weight;
        this.unit = unit;  // Initialize the unit
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getProductName() {
        return productName;
    }

    public int getWeight() {
        return weight;
    }

    public String getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
