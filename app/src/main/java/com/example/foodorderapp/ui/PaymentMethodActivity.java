package com.example.foodorderapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderapp.R;

public class PaymentMethodActivity extends AppCompatActivity {

    private Button btnGcash, btnCard, btnCash;
    private String foodName, preOrderDateTime;
    private boolean isPreOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        btnGcash = findViewById(R.id.btnGcash);
        btnCard = findViewById(R.id.btnCard);
        btnCash = findViewById(R.id.btnCash);

        Intent intent = getIntent();
        foodName = intent.getStringExtra("foodName");
        isPreOrder = intent.getBooleanExtra("isPreOrder", false);
        preOrderDateTime = intent.getStringExtra("preOrderDateTime");

        btnGcash.setOnClickListener(v -> showGcashDialog());
        btnCard.setOnClickListener(v -> showCardDialog());
        btnCash.setOnClickListener(v -> showCashDialog());
    }

    private void showGcashDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan to Pay with GCash");

        ImageView qrImage = new ImageView(this);
        qrImage.setImageResource(R.drawable.qr); // Make sure this QR code image is in your drawable
        builder.setView(qrImage);

        builder.setPositiveButton("Done", (dialog, which) -> goToReceipt("GCash"));
        builder.setCancelable(true);
        builder.show();
    }

    private void showCardDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Card PIN");

        EditText inputPin = new EditText(this);
        inputPin.setHint("Enter 4-digit PIN");
        inputPin.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(inputPin);

        builder.setPositiveButton("Submit", (dialog, which) -> {
            String pin = inputPin.getText().toString();
            if (pin.equals("1234")) { // Fake PIN
                goToReceipt("Card");
            } else {
                Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showCashDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cash Payment");
        builder.setMessage("Please go to the counter to complete your payment.");
        builder.setPositiveButton("OK", (dialog, which) -> goToReceipt("Cash"));
        builder.show();
    }

    private void goToReceipt(String paymentMethod) {
        Intent intent = new Intent(this, ReceiptActivity.class);
        intent.putExtra("foodName", foodName);
        intent.putExtra("isPreOrder", isPreOrder);
        intent.putExtra("preOrderDateTime", preOrderDateTime);
        intent.putExtra("paymentMethod", paymentMethod);
        startActivity(intent);
        finish();
    }
}
