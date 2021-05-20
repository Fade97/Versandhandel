package de.volkswagen.f73;

import de.volkswagen.f73.Product.Tax;

public class Storage {
    private static Product[] products = { 
            new Product("Bannane", 0.3, 3, Tax.REDUCED_TAX),
            new Product("Apfel", 0.5, 5, Tax.REDUCED_TAX), 
            new Product("Birne", 0.4, 7, Tax.REDUCED_TAX),
            new Product("TV", 249.99, 3, Tax.TAX) };

    public static final int MAX_PRODUCTS = 4;
    
    public static Product[] getProducts() {
        return products;
    }
    
    public static int getStock(Product product) {
        for (int i = 0; i < MAX_PRODUCTS; i++) {
            if (products[i].equals(product)) {
                return products[i].getQuantity();
            }
        }
        return 0;
    }
    
    public static void setStock(Product product, int quantity) {
        for (int i = 0; i < MAX_PRODUCTS; i++) {
            if (products[i].equals(product)) {
                products[i].setQuantity(quantity);
            }
        }
    }
}