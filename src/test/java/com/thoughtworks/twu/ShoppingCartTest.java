package com.thoughtworks.twu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartTest {

    @Test
    public void emptyCartShouldHaveZeroTotal() {
        ShoppingCart cart = new ShoppingCart();

        assertEquals(cart.getTotal(), 0d);
    }

    @Test
    public void addingItemsShouldResultInCorrectRunningTotal() {
        ShoppingCart cart = new ShoppingCart();

        cart.addToCart(new Item("Banana", 5, true));
        cart.addToCart(new Item("Beans", 10, true));
        cart.addToCart(new Item("Bread", 1, true));

        assertEquals(cart.getTotal(), 16d);
        assertEquals(cart.getItemCount(), 3);
    }

    @Test
    public void removingItemsShouldReduceTotalToZero() {
        ShoppingCart cart = new ShoppingCart();

        cart.addToCart(new Item("Banana", 5, true));
        cart.addToCart(new Item("Beans", 10, true));
        cart.addToCart(new Item("Bread", 1, true));

        cart.removeItems();

        assertEquals(cart.getTotal(), 0d);
        assertEquals(cart.getItemCount(), 0);
    }

    @Test
    public void checkingOutItemsShouldResultInCorrectTotalWithShippingAndTaxAdded() {
        ShoppingCart cart = new ShoppingCart();

        cart.addToCart(new Item("Banana", 5, true));
        cart.addToCart(new Item("Beans", 10, false));
        cart.addToCart(new Item("Bread", 1, true));

        assertEquals(cart.getCheckoutPrice(), 28d);
    }

    @Test
    public void shouldCalculateLoyaltyPointsForValuableItems() {
        ShoppingCart cart = new ShoppingCart();

        cart.addToCart(new Item("Rolex", 500, true));
        cart.addToCart(new Item("Dishwasher", 200, true));
        cart.addToCart(new Item("Banana", 5, false));

        assertEquals(cart.calculateLoyaltyPoints(), 70);
    }

    @Test
    public void addingVoucherShouldReduceTotalByCorrectAmount() {
        ShoppingCart cart = new ShoppingCart();

        cart.addToCart(new Item("Milk", 5, true));
        cart.addToCart(new Item("Slippers", 20, true));

        cart.applyVoucher(new Voucher(10));

        assertEquals(cart.getTotal(), 15d);
    }

    @Test
    public void checkingOutClearsTheCartAndPrintsReceipt() {
        ShoppingCart cart = new ShoppingCart();

        cart.addToCart(new Item("Banana", 5, true));
        cart.addToCart(new Item("Beans", 10, true));
        cart.addToCart(new Item("Bread", 1, true));

        String output = cart.printReceiptAndCheckOut();

        assertTrue(output.contains("*** Super Item Store ***"));
        assertTrue(output.contains("Banana price: 5.00"));
        assertTrue(output.contains("Beans price: 10.00"));
        assertTrue(output.contains("Bread price: 1.00"));
        assertTrue(output.contains("No of items: 3"));
        assertTrue(output.contains("Total: 26.00"));
        assertTrue(output.contains("You earned 0 SuperPoints with this purchase"));
        assertTrue(output.contains("*** Come back soon! ***"));
        assertEquals(cart.getTotal(), 0d);
        assertEquals(cart.getItemCount(), 0);
    }
}