package com.example.foodorderapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int calories;
    private int protein;
    private double price;
    private int imageResId;

    // No-argument constructor required by Room
    public Food() {
    }

    // Full constructor
    public Food(int id, String name, int calories, int protein, double price, int imageResId) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public int getProtein() { return protein; }
    public void setProtein(int protein) { this.protein = protein; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }

    // Method to get image URI
    public String getImageUri() {
        return "android.resource://com.example.foodorderapp/" + imageResId;
    }
}
