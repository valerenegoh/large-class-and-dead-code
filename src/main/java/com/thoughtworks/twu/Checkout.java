package com.thoughtworks.twu;

import java.util.List;

public class Checkout {

    private ShoppingCart cart;
    private List<Item> items;
    private double total;

    public Checkout(ShoppingCart cart) {
        this.cart = cart;
        items = cart.getItems();
        total = cart.getTotal();
    }

    public void applyVoucher(Voucher voucher) {
        total -= voucher.getAmount();
    }

    public int calculateLoyaltyPoints() {
        int loyaltyPoints = 0;
        for (Item item : items) {
            if(item.getPrice() > 10) {
                loyaltyPoints+= item.getPrice()/10;
            }
        }
        return loyaltyPoints;
    }

    public double getCheckoutPrice() {
        calculateTax();
        calculateShipping();

        return total;
    }

    private void calculateTax() {
        double taxToAdd = 0;
        for(Item item : items) {
            if(!item.isTaxFree()) {
                taxToAdd += item.getPrice() * 0.2;
            }
        }
        total += taxToAdd;
    }

    private void calculateShipping() {
        if (!items.isEmpty()) this.total += 10;
    }

    public String printReceiptAndCheckOut() {
        String receipt = new Receipt(this).print();
        cart.removeItems();
        return receipt;
    }

    public double getTotal() {
        return total;
    }

    public List<Item> getItems() {
        return items;
    }
}