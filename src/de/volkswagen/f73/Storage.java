package de.volkswagen.f73;

import de.volkswagen.f73.Product.Category;

public class Storage {
    public static Product[] products;

    private static int maxProducts = 10;
    
    public static int getMaxProducts() {
        return maxProducts;
    }
    
    public static void setMaxProducts(int maxAmount) {
        maxProducts = maxAmount;
    }
    
    public static void setProducts(Product[] productsFromFile) {
        products = productsFromFile;
    }
    
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
        for (int i = 0; i < maxProducts; i++) {
            if (products[i].equals(product)) {
                return products[i].getQuantity();
            }
        }
        return 0;
    }
    
    public static void setStock(Product product, int quantity) {
        for (int i = 0; i < maxProducts; i++) {
            if (products[i].equals(product)) {
                products[i].setQuantity(quantity);
            }
        }
    }
}