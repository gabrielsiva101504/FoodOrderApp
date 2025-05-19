package com.example.foodorderapp.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodorderapp.R;

import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {

    RadioButton radioPreOrder, radioTakeOut;
    RadioGroup orderTypeGroup;
    LinearLayout dateTimeLayout;
    Button buttonSelectDate, buttonSelectTime, buttonPlaceOrder;
    TextView textFoodName, textFoodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize views
        radioPreOrder = findViewById(R.id.radioPreOrder);
        radioTakeOut = findViewById(R.id.radioTakeOut);
        orderTypeGroup = findViewById(R.id.orderTypeGroup);
        dateTimeLayout = findViewById(R.id.dateTimeLayout);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonSelectTime = findViewById(R.id.buttonSelectTime);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);
        textFoodName = findViewById(R.id.textFoodName);
        textFoodPrice = findViewById(R.id.textFoodPrice);

        // Receive food data from intent
        String foodName = getIntent().getStringExtra("foodName");
        double foodPrice = getIntent().getDoubleExtra("foodPrice", 0.0);

        // Display food name and price
        textFoodName.setText(foodName);
        textFoodPrice.setText("₱" + String.format("%.2f", foodPrice));

        // Show/hide date-time picker
        orderTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioPreOrder) {
                dateTimeLayout.setVisibility(View.VISIBLE);
            } else {
                dateTimeLayout.setVisibility(View.GONE);
            }
        });

        // Date picker
        buttonSelectDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(OrderActivity.this,
                    (view, year, month, day) -> Toast.makeText(this,
                            "Selected Date: " + (month + 1) + "/" + day + "/" + year,
                            Toast.LENGTH_SHORT).show(),
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        // Time picker
        buttonSelectTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(OrderActivity.this,
                    (view, hour, minute) -> Toast.makeText(this,
                            "Selected Time: " + hour + ":" + String.format("%02d", minute),
                            Toast.LENGTH_SHORT).show(),
                    c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        // Final order button
        buttonPlaceOrder.setOnClickListener(v -> {
            String orderType = radioPreOrder.isChecked() ? "Pre-order" : "Take out now";
            Toast.makeText(this, foodName + " - " + orderType + " confirmed!", Toast.LENGTH_SHORT).show();
        });
    }
}
