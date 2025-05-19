package com.example.foodorderapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class OrderEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String foodName;
    public String paymentMethod;
    public String dateTime;

    public OrderEntity(String foodName, String paymentMethod, String dateTime) {
        this.foodName = foodName;
        this.paymentMethod = paymentMethod;
        this.dateTime = dateTime;
    }
}
