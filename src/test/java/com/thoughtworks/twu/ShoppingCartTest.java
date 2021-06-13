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
}