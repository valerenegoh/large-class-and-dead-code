package com.thoughtworks.twu;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;
    private double total;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.total = 0;
    }

    public void addToCart(Item item) {
        items.add(item);
        total+= item.getPrice();
    }

    public double getTotal() {
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void removeItems() {
        items = new ArrayList<>();
        total = 0;
    }

    public List<Item> getItems() {
        return items;
    }
}
