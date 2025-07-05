package com.example.bsn;

public class Product {
    private String name;
    private String price;
    private String weight;
    private int imageResId;


    public Product(String name, String price, String weight, int imageResId) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getWeight() { return weight; }
    public int getImageResId() { return imageResId; }
}
