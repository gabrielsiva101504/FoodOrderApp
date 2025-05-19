package com.example.foodorderapp.data;

public class Order {
    private Food food;
    private boolean isPreOrder;

    public Order(Food food, boolean isPreOrder) {
        this.food = food;
        this.isPreOrder = isPreOrder;
    }

    public Food getFood() {
        return food;
    }

    public boolean isPreOrder() {
        return isPreOrder;
    }
}
