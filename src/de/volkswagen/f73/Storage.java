package de.volkswagen.f73;

import de.volkswagen.f73.Product.Category;

public class Storage {
    private static Product[] products = { 
            new Product("Bannane", 0.3, 3, Category.FRUITS , TaxRates.REDUCED_TAX),
            new Product("Apfel", 0.5, 5, Category.FRUITS , TaxRates.REDUCED_TAX), 
            new Product("Birne", 0.4, 7, Category.FRUITS , TaxRates.REDUCED_TAX),
            new Product("TV", 249.99, 3, Category.NON_FOOD , TaxRates.TAX) };

    public static final int MAX_PRODUCTS = products.length;
    
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