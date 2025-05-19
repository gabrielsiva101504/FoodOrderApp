package com.example.foodorderapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PaymentDao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FoodOrderDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "payments";

    public PaymentDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "food_name TEXT," +
                "is_preorder INTEGER," +
                "preorder_datetime TEXT," +
                "payment_method TEXT," +
                "payment_status TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not needed for now
    }

    public void insertPayment(String foodName, boolean isPreOrder, String dateTime, String method) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("food_name", foodName);
        values.put("is_preorder", isPreOrder ? 1 : 0);
        values.put("preorder_datetime", dateTime);
        values.put("payment_method", method);
        values.put("payment_status", "Completed");

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
