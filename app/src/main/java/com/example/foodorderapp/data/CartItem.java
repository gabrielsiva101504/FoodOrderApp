package com.example.foodorderapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public double price;
    public int quantity;        // default 1
    public String imageUri;     // optional – leave null if you don’t use images

    public CartItem(String name, double price, int quantity, String imageUri) {
        this.name      = name;
        this.price     = price;
        this.quantity  = quantity;
        this.imageUri  = imageUri;
    }
}
