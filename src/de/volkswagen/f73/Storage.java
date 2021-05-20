package de.volkswagen.f73;

import de.volkswagen.f73.Product.Tax;

public class Storage {
    private static Product[] products = { 
            new Product("Bannane", 0.3, 3, Tax.REDUCED_TAX),
            new Product("Apfel", 0.5, 5, Tax.REDUCED_TAX), 
            new Product("Birne", 0.4, 7, Tax.REDUCED_TAX),
            new Product("TV", 249.99, 3, Tax.TAX) };

    public static final int MAX_PRODUCTS = products.length;
    
    public static Product[] getProducts() {
        return products;
    }
}