package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.Product;

import java.util.HashMap;
import java.util.Map;

/* The data structure has been changed from the list to the dictionary to store the
   quantity of products in the shopping cart */
public class Basket {

    private final Map<Product, Integer> products;

    public Basket() {
        products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        }
        else {
            products.put(product, quantity);
        }
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
