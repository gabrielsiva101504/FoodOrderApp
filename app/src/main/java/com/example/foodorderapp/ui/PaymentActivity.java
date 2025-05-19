package com.example.foodorderapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderapp.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment); // This line can remain for now

        String foodName = getIntent().getStringExtra("foodName");
        boolean isPreOrder = getIntent().getBooleanExtra("isPreOrder", false);
        String preOrderDateTime = getIntent().getStringExtra("preOrderDateTime");

        // 🔄 Redirect to payment method screen immediately
        Intent intent = new Intent(PaymentActivity.this, PaymentMethodActivity.class);
        intent.putExtra("foodName", foodName);
        intent.putExtra("isPreOrder", isPreOrder);
        intent.putExtra("preOrderDateTime", preOrderDateTime);
        startActivity(intent);
        finish(); // Finish current activity so user can't go back to this screen
    }
}
