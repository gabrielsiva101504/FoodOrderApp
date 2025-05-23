package com.example.foodorderapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insertOrder(OrderEntity order);

    @Query("SELECT * FROM orders ORDER BY id DESC")
    List<OrderEntity> getAllOrders();
}
