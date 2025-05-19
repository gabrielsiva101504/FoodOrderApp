package com.example.foodorderapp.data;

import com.example.foodorderapp.R;

import java.util.Arrays;
import java.util.List;

public class FoodDao {
    public static List<Food> getFoodList() {
        return Arrays.asList(
                new Food(1, "Chicken Rice Bowl", 550, 30, 120.0, R.drawable.chicken_bowl),
                new Food(2, "Beef Burger", 700, 35, 150.0, R.drawable.beef_burger),
                new Food(3, "Salad Bowl", 300, 10, 100.0, R.drawable.salad_bowl),
                new Food(4, "Spaghetti Bolognese", 600, 25, 130.0, R.drawable.spaghetti_bolognese),
                new Food(5, "Grilled Salmon", 500, 32, 180.0, R.drawable.grilled_salmon),
                new Food(6, "Veggie Wrap", 350, 8, 90.0, R.drawable.veggie_wrap),
                new Food(7, "Pork BBQ Skewers", 480, 28, 110.0, R.drawable.pork_bbq_skewers),
                new Food(8, "Shrimp Tempura", 450, 22, 140.0, R.drawable.shrimp_tempura),
                new Food(9, "Cheese Pizza", 650, 20, 130.0, R.drawable.cheese_pizza),
                new Food(10, "Tuna Sandwich", 400, 18, 100.0, R.drawable.tuna_sandwich),
                new Food(11, "Chicken Nuggets", 500, 25, 115.0, R.drawable.chicken_nuggets),
                new Food(12, "Beef Tapa", 520, 27, 125.0, R.drawable.beef_tapa),
                new Food(13, "Fried Rice", 430, 12, 85.0, R.drawable.fried_rice),
                new Food(14, "Korean Bibimbap", 550, 24, 145.0, R.drawable.korean_bibimbap),
                new Food(15, "Lumpiang Shanghai", 420, 14, 95.0, R.drawable.lumpiang_shanghai),
                new Food(16, "Mushroom Soup", 300, 6, 75.0, R.drawable.mushroom_soup),
                new Food(17, "Pancit Canton", 460, 14, 90.0, R.drawable.pancit_canton),
                new Food(18, "Sizzling Sisig", 750, 40, 160.0, R.drawable.sizzling_sisig),
                new Food(19, "Tocino with Rice", 530, 26, 120.0, R.drawable.tocino_rice),
                new Food(20, "Vegetable Stir Fry", 320, 9, 85.0, R.drawable.vegetable_stir_fry)
        );
    }
}
