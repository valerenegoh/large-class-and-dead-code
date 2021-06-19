package com.thoughtworks.twu;

import java.util.List;

public class Receipt {

    public static final String RECEIPT_HEADER = "*** Super Item Store ***";
    public static final String RECEIPT_TOTAL_AMOUNT = "Total: %.2f%n";
    public static final String RECEIPT_EARNED_LOYALTY_POINTS = "You earned %d SuperPoints with this purchase";
    public static final String RECEIPT_FOOTER = "*** Come back soon! ***";
    public static final String RECEIPT_ITEM_WITH_PRICE = "%s price: %.2f%n";
    public static final String RECEIPT_TOTAL_NUMBER_OF_ITEMS = "No of items: %d%n";

    private Checkout checkout;

    public Receipt(Checkout checkout) {
        this.checkout = checkout;
    }

    public String print() {
        StringBuilder output = new StringBuilder();

        output.append(RECEIPT_HEADER);
        output.append(System.lineSeparator());

        appendCartItems(output);

        output.append(String.format(RECEIPT_TOTAL_AMOUNT, checkout.getCheckoutPrice()));
        output.append(String.format(RECEIPT_EARNED_LOYALTY_POINTS, checkout.calculateLoyaltyPoints()));
        output.append(System.lineSeparator());

        output.append(RECEIPT_FOOTER);

        return output.toString();
    }

    private void appendCartItems(StringBuilder output) {
        List<Item> items = checkout.getItems();
        items.forEach(item -> output.append(String.format(RECEIPT_ITEM_WITH_PRICE, item.getName(), item.getPrice())));
        output.append(String.format(RECEIPT_TOTAL_NUMBER_OF_ITEMS, items.size()));
    }
}