package com.thoughtworks.twu;

public class Item {
    private String name;
    private double price;
    private boolean isTaxFree;

    public Item(String name, int price, boolean isTaxFree) {
        this.name = name;
        this.price = price;
        this.isTaxFree = isTaxFree;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isTaxFree() {
        return isTaxFree;
    }
}
