package de.volkswagen.f73;

import java.util.Random;

public class Product {
    private String name;
    private double price;
    private String inventoryNr = null;
    private int quantity;
    private TaxRates tax;
    private Category category;
    
    public enum Category{FRUITS, VEGETABLES, MEAT, MILK_PRODUCTS, BREAD, NON_FOOD;}

    private static final int INVENTORY_NR_LENGTH = 5;
    
    private static String[] inventoryNumbers = new String[Storage.MAX_PRODUCTS];

    //----- Konstruktoren -----
    public Product(String name, double price, int quantity, Category category, TaxRates tax) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.tax = tax;
        this.category = category;
        if (this.inventoryNr == null) {
            this.inventoryNr = generateInventoryNr();
        }
    }
    
    public Product(Product product) {
        this(product.getName(), product.getPrice(), product.getQuantity(), product.getCategory(), product.getTax());
        this.setInventoryNr(product.getInventoryNr());
    }

    //----- Getter -----
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getInventoryNr() {
        return this.inventoryNr;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public TaxRates getTax() {
        return this.tax;
    }
    
    public Category getCategory() {
        return this.category;
    }

    //----- Setter -----
    public void setQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            this.quantity = newQuantity;
        }
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
    
    private void setInventoryNr(String invNr) {
        this.inventoryNr = invNr;
    }

    //----- Methoden -----
    private String generateInventoryNr() {
        Random rand = new Random();
        String inventoryNr = "";
        boolean isValidNr = true;

        do {
            // Nummer String generieren
            for (int i = 0; i < INVENTORY_NR_LENGTH; i++) {
                inventoryNr += rand.nextInt(10);
            }
            // Prüfen ob Nr bereits vergeben
            for (String i : inventoryNumbers) {
                if (i != null && i.equals(inventoryNr)) {
                    isValidNr = false;
                }
            }
        } while (!isValidNr);
        //Neue Nummer aufnehmen an nächster Freier Stelle
        for (int i = 0; i < Storage.MAX_PRODUCTS; i++) {
            if (inventoryNumbers[i] == null) {
                inventoryNumbers[i] = inventoryNr;
                break;
            }
        }
        return inventoryNr;
    }
    
    public boolean equals(Product product) {
        return this.getInventoryNr() == product.getInventoryNr();
    }
}