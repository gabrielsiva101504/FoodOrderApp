package com.example.foodorderapp.data;

public class Food {
    private int id;
    private String name;
    private int calories;
    private int protein;
    private double price;
    private int imageResId;

    public Food(int id, String name, int calories, int protein, double price, int imageResId) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.price = price;
        this.imageResId = imageResId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}
