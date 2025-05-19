package com.example.foodorderapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.data.AppDatabase;
import com.example.foodorderapp.data.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartVH> {

    public interface CartListener {                // activity implements this
        void onCartUpdated();
    }

    private final List<CartItem> cartList;
    private final CartListener listener;

    public CartAdapter(List<CartItem> cartList, CartListener listener) {
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartVH(v);
    }

    @Override public void onBindViewHolder(@NonNull CartVH h, int pos) {
        CartItem item = cartList.get(pos);

        h.txtName.setText(item.name);
        h.txtQty .setText("Qty: " + item.quantity);
        h.txtPrice.setText("₱" + String.format("%.2f", item.price * item.quantity));

        if (item.imageUri != null) h.img.setImageURI(android.net.Uri.parse(item.imageUri));

        h.btnRemove.setOnClickListener(v -> {
            AppDatabase db = AppDatabase.getInstance(v.getContext());
            db.cartDao().delete(item);
            cartList.remove(pos);
            notifyItemRemoved(pos);
            listener.onCartUpdated();
        });
    }

    @Override public int getItemCount() { return cartList.size(); }

    static class CartVH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView  txtName, txtQty, txtPrice;
        ImageButton btnRemove;
        CartVH(View v) {
            super(v);
            img       = v.findViewById(R.id.imgCartFood);
            txtName   = v.findViewById(R.id.txtCartName);
            txtQty    = v.findViewById(R.id.txtCartQuantity);
            txtPrice  = v.findViewById(R.id.txtCartPrice);
            btnRemove = v.findViewById(R.id.btnRemove);
        }
    }
}
