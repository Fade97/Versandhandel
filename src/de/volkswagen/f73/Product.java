package de.volkswagen.f73;

import java.util.Random;

public class Product {
    private String name;
    private double price;
    //private String inventoryNr = null; <---- temporär ersetzt
    private int inventoryNr =  0;
    private static int inventoryNrCount = 0;
    private int quantity;
    private TaxRates tax;
    private Category category;
    
    public enum Category{NO_CATEGORY, FRUITS, VEGETABLES, MEAT, FISH, MILK_PRODUCTS, BREAD, DRINKS, NON_FOOD;}

    //private static final int INVENTORY_NR_LENGTH = 5; <---- temporär ungenutzt
    //private static String[] inventoryNumbers = new String[Storage.MAX_PRODUCTS]; <---- temporär ersetzt
    

    //----- Konstruktoren -----
    public Product(String name, double price, int quantity, Category category, TaxRates tax) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.tax = tax;
        this.category = category;
        if (this.inventoryNr == 0) {
            //this.inventoryNr = generateInventoryNr(); <---- generateInventoryNr() temporär ersetzt durch einfache variablenhochzählung
            this.inventoryNr = 1000 + inventoryNrCount++;
        }
    }
    
    public Product(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.tax = product.getTax();
        this.category = product.getCategory();
        this.setInventoryNr(product.getInventoryNr());
    }

    //----- Getter -----
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

//    public String getInventoryNr() {
//        return this.inventoryNr;
//    }
    
    public int getInventoryNr() {
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
    
//    private void setInventoryNr(String invNr) {
//        this.inventoryNr = invNr;
//    }
    
    private void setInventoryNr(int invNr) {
        this.inventoryNr = invNr;
    }

    //----- Methoden -----
//    private String generateInventoryNr() {
//        Random rand = new Random();
//        String inventoryNr = "";
//        boolean isValidNr = true;
//
//        do {
//            // Nummer String generieren
//            for (int i = 0; i < INVENTORY_NR_LENGTH; i++) {
//                inventoryNr += rand.nextInt(10);
//            }
//            // Prüfen ob Nr bereits vergeben
//            for (String i : inventoryNumbers) {
//                if (i != null && i.equals(inventoryNr)) {
//                    isValidNr = false;
//                }
//            }
//        } while (!isValidNr);
//        //Neue Nummer aufnehmen an nächster Freier Stelle
//        for (int i = 0; i < Storage.MAX_PRODUCTS; i++) {
//            if (inventoryNumbers[i] == null) {
//                inventoryNumbers[i] = inventoryNr;
//                break;
//            }
//        }
//        return inventoryNr;
//    }
    
    public boolean equals(Product product) {
        return this.getInventoryNr() == product.getInventoryNr();
    }
}