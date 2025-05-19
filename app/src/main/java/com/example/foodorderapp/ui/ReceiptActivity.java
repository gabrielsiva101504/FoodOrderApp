package com.example.foodorderapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderapp.R;

public class ReceiptActivity extends AppCompatActivity {

    TextView textReceiptDetails;
    Button btnShareReceipt, btnOrderAgain, btnBackToHome, btnViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        textReceiptDetails = findViewById(R.id.textReceiptDetails);
        btnShareReceipt = findViewById(R.id.btnShareReceipt);
        btnOrderAgain = findViewById(R.id.btnOrderAgain);
        btnBackToHome = findViewById(R.id.btnBackToHome);
        btnViewCart = findViewById(R.id.btnViewCart);

        String foodName = getIntent().getStringExtra("foodName");
        String paymentMethod = getIntent().getStringExtra("paymentMethod");
        String time = getIntent().getStringExtra("time");

        String receiptText = "Food: " + foodName + "\nPayment: " + paymentMethod + "\nTime: " + time;
        textReceiptDetails.setText(receiptText);

        btnShareReceipt.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Food Order Receipt");
            shareIntent.putExtra(Intent.EXTRA_TEXT, receiptText);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        btnOrderAgain.setOnClickListener(v -> {
            Intent intent = new Intent(ReceiptActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(ReceiptActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        btnViewCart.setOnClickListener(v -> {
            Intent intent = new Intent(ReceiptActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
