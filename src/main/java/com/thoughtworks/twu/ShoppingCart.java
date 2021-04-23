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

    public void applyVoucher(Voucher voucher) {
        total -= voucher.getAmount();
    }

    public double getTotal() {
        return total;
    }

    public String printReceiptAndCheckOut() {
        StringBuilder output = new StringBuilder();

        output.append("*** Super Item Store ***");
        output.append(System.lineSeparator());
        items.forEach(item -> output.append(String.format("%s price: %.2f%n", item.getName(), item.getPrice())));
        output.append(String.format("No of items: %d%n", items.size()));
        output.append(String.format("Total: %.2f%n", getCheckoutPrice()));
        output.append(String.format("You earned %d SuperPoints with this purchase", calculateLoyaltyPoints()));
        output.append(System.lineSeparator());
        output.append("*** Come back soon! ***");

        items = new ArrayList<>();
        total = 0;

        return output.toString();
    }

    public void addToCart(Item item) {
        items.add(item);
        total+= item.getPrice();
    }

    public void removeItems() {
        items = new ArrayList<>();
        total = 0;
    }

    public int getItemCount() {
        return items.size();
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
//        total += items.stream().filter(item -> !item.isTaxFree())
//                .map(item -> (double) item.getPrice())
//                .reduce(0d, (current, price) -> current+= price * 0.2 );

        double taxToAdd = 0;

        for(Item item : items) {
            if(item.isTaxFree()) {
                continue;
            } else {
                taxToAdd += item.getPrice() * 0.2;
            }
        }

        total += taxToAdd;
    }

    private void calculateShipping() {

        // TODO Awaiting confirmation
//        if(total > 100) {
//            return;
//        }

        if (!items.isEmpty()) {
            this.total += 10;
        }
    }
}
