package com.example.foodorderapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapter.CartAdapter;
import com.example.foodorderapp.data.AppDatabase;
import com.example.foodorderapp.data.CartItem;

import java.util.List;

/**
 * Cart screen – shows items, total, and buttons to checkout / order again / home.
 * Requires CartAdapter to call listener.onCartUpdated() whenever its data changes.
 */
public class CartActivity extends AppCompatActivity implements CartAdapter.CartListener {

    private RecyclerView recyclerView;
    private TextView textTotal;
    private Button btnCheckout, btnOrderAgain, btnGoHome;
    private CartAdapter cartAdapter;

    private AppDatabase db;
    private List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView  = findViewById(R.id.recyclerViewCart);
        textTotal     = findViewById(R.id.textTotal);
        btnCheckout   = findViewById(R.id.btnCheckout);
        btnOrderAgain = findViewById(R.id.btnOrderAgain);
        btnGoHome     = findViewById(R.id.btnGoHome);

        db            = AppDatabase.getInstance(this);
        cartItemList  = db.cartDao().getAllCartItems();

        cartAdapter   = new CartAdapter(cartItemList, this);   //  <-- pass listener
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        updateTotal();

        btnCheckout.setOnClickListener(v ->
                startActivity(new Intent(CartActivity.this, PaymentActivity.class)));

        btnOrderAgain.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, MainActivity.class));
            finish();
        });

        btnGoHome.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, HomeActivity.class));
            finish();
        });
    }

    /* ===== CartAdapter.CartListener ===== */
    @Override
    public void onCartUpdated() {
        // Re-query DB and refresh total when adapter signals a change
        cartItemList.clear();
        cartItemList.addAll(db.cartDao().getAllCartItems());
        cartAdapter.notifyDataSetChanged();
        updateTotal();
    }

    /* ===== helpers ===== */
    private void updateTotal() {
        double total = 0;
        for (CartItem item : cartItemList) {
            total += item.price * item.quantity;   // use public fields or getters
        }
        textTotal.setText("Total: ₱" + String.format("%.2f", total));
    }
}
