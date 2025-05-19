package com.example.foodorderapp.adapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.data.Food;
import com.example.foodorderapp.ui.PaymentActivity;

import java.util.Calendar;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> foodList;
    private Context context;

    public void updateList(List<Food> newList) {
        this.foodList = newList;
        notifyDataSetChanged();
    }

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);

        holder.foodImage.setImageResource(food.getImageResId());
        holder.foodName.setText(food.getName());
        holder.foodNutrition.setText("Calories: " + food.getCalories() + ", Protein: " + food.getProtein() + "g");
        holder.foodPrice.setText("₱" + food.getPrice());

        holder.btnTakeOut.setOnClickListener(v -> {
            Intent intent = new Intent(context, PaymentActivity.class);
            intent.putExtra("foodName", food.getName());
            intent.putExtra("isPreOrder", false);
            context.startActivity(intent);
        });

        holder.btnPreOrder.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();

            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    (view1, year, month, dayOfMonth) -> {

                        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                                (timePicker, hourOfDay, minute) -> {

                                    String dateTime = String.format("%04d-%02d-%02d %02d:%02d",
                                            year, month + 1, dayOfMonth, hourOfDay, minute);

                                    Intent intent = new Intent(context, PaymentActivity.class);
                                    intent.putExtra("foodName", food.getName());
                                    intent.putExtra("isPreOrder", true);
                                    intent.putExtra("preOrderDateTime", dateTime);
                                    context.startActivity(intent);

                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true);

                        timePickerDialog.show();

                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName, foodNutrition, foodPrice;
        Button btnTakeOut, btnPreOrder;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodNutrition = itemView.findViewById(R.id.foodNutrition);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            btnTakeOut = itemView.findViewById(R.id.btnTakeOut);
            btnPreOrder = itemView.findViewById(R.id.btnPreOrder);
        }
    }
}
