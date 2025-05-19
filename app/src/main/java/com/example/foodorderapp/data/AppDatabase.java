package com.example.foodorderapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CartItem.class /* , other entities */}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CartDao cartDao();
    // add other DAOs (foodDao(), orderDao(), …) here as needed

    public static synchronized AppDatabase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx.getApplicationContext(),
                            AppDatabase.class, "food_order_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()        // demo only – use background threads in prod
                    .build();
        }
        return instance;
    }
}
