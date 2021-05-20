package de.volkswagen.f73;

import java.util.Random;

public class Product {
    private String name;
    private double price;
    private String inventoryNr = null;
    private int quantity;
    private Tax tax;

    private static final int INVENTORY_NR_LENGTH = 5;
    
    private static String[] inventoryNumbers = new String[Storage.MAX_PRODUCTS];

    public enum Tax {
        TAX(19.0), REDUCED_TAX(7.0);

        double percentage;

        Tax(double percentage) {
            this.percentage = percentage;
        }

        public double getPercentage() {
            return this.percentage;
        }
    }

    //----- Konstruktoren -----
    public Product(String name, double price, int quantity, Tax tax) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.tax = tax;
        if (this.inventoryNr == null) {
            this.inventoryNr = generateInventoryNr();
        }
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

    public Tax getTax() {
        return this.tax;
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
}