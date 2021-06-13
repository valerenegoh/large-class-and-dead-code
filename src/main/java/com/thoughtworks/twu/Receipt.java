package com.thoughtworks.twu;

import java.util.List;

public class Receipt {

    private Checkout checkout;

    public Receipt(Checkout checkout) {
        this.checkout = checkout;
    }

    public String print() {
        StringBuilder output = new StringBuilder();

        output.append("*** Super Item Store ***");
        output.append(System.lineSeparator());

        appendCartItems(output);

        output.append(String.format("Total: %.2f%n", checkout.getCheckoutPrice()));
        output.append(String.format("You earned %d SuperPoints with this purchase", checkout.calculateLoyaltyPoints()));
        output.append(System.lineSeparator());

        output.append("*** Come back soon! ***");

        return output.toString();
    }

    private void appendCartItems(StringBuilder output) {
        List<Item> items = checkout.getItems();
        items.forEach(item -> output.append(String.format("%s price: %.2f%n", item.getName(), item.getPrice())));
        output.append(String.format("No of items: %d%n", items.size()));
    }
}