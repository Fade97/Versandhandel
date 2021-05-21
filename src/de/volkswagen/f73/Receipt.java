package de.volkswagen.f73;

public class Receipt {
    private Product[] shoppingCart = new Product[Storage.MAX_PRODUCTS];
    private double totalPrice = 0.0;
    private boolean isPaid;
    private int receiptNr =  0;
    private static int receiptNrCount = 0;
    
    public Receipt() {
        this.receiptNr = 1000 + receiptNrCount++;
        System.out.println(receiptNr);
    }
    
    //----- Getter -----
    public int getReceiptNr() {
        return this.receiptNr;
    }
    
    public Product[] getShoppingCart() {
        return this.shoppingCart;
    }
    
    public double getTotalPrice() {
        return this.totalPrice;
    }
    
    public boolean isPaid() {
        return this.isPaid;
    }
    
    public int getNumberOfItems() {
        int numberOfItems = 0;
        for (Product product : shoppingCart) {
            if (product != null) {
                numberOfItems += product.getQuantity();
            }
        }
        return numberOfItems;
    }
    
    //----- Methoden -----
    public void addProductToCart(Product productToAdd, int quantity) {
        if(quantity < 0) {
            return;
        }
        for (int i = 0; i < shoppingCart.length; i++) {
            
            if (shoppingCart[i] == null && Storage.getStock(productToAdd) - quantity >= 0) {
                shoppingCart[i] = new Product(productToAdd);
                shoppingCart[i].setQuantity(quantity);
                break;
            } else if (shoppingCart[i].equals(productToAdd)) {
                shoppingCart[i].setQuantity(shoppingCart[i].getQuantity() + quantity);
                break;
            }
        }
        calculateTotalPrice();
    }
    
    private void calculateTotalPrice() {
        double sum = 0.0;
        for (Product product : shoppingCart) {
            if (product != null) {
                sum += ((double)product.getQuantity()) * ((double)product.getPrice());
            }
        }
        this.totalPrice = sum;
    }
    
    //RemoveFromCart Methode
}