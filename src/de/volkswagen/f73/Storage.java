package de.volkswagen.f73;

import de.volkswagen.f73.Product.Category;

public class Storage {
    private static Product[] products = { 
            new Product("Bannane", 0.39, 3, Category.FRUITS , TaxRates.REDUCED_TAX),
            new Product("Apfel", 0.59, 5, Category.FRUITS , TaxRates.REDUCED_TAX), 
            new Product("Birne", 0.49, 7, Category.FRUITS , TaxRates.REDUCED_TAX),
            new Product("Paprika", 0.79, 8, Category.VEGETABLES , TaxRates.REDUCED_TAX),
            new Product("Nackensteak", 4.95, 3, Category.MEAT , TaxRates.REDUCED_TAX),
            new Product("Lachs", 2.49, 5, Category.FISH , TaxRates.REDUCED_TAX),
            new Product("Joghurt", 1.49, 7, Category.MILK_PRODUCTS , TaxRates.REDUCED_TAX),
            new Product("Baguette", 2.29, 4, Category.BREAD , TaxRates.REDUCED_TAX),
            new Product("Cola", 1.99, 6, Category.DRINKS , TaxRates.REDUCED_TAX),
            new Product("TV", 249.99, 3, Category.NON_FOOD , TaxRates.TAX) };

    public static final int MAX_PRODUCTS = 10;
    
    //----- Methoden -----
    public static Product[] getProducts() {
        return products;
    }
    
    public static Product[] getProducts(Category category) {
        int itemCount = 0;
        for (Product product : products) {
            if (product.getCategory() == category) {
                itemCount++;
            }
        }
        Product[] selectedProducts;
        if (itemCount > 0) {
            selectedProducts = new Product[itemCount];
            for (int i = 0; i < selectedProducts.length; i++) {
                for (int j = 0; j < products.length; j++) {
                    if (products[i].getCategory() == category) {
                        selectedProducts[i] = products[j];
                    }
                }
            }
        } else {
            return null;
        }
        return selectedProducts;
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