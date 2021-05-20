package de.volkswagen.f73;

public class Receipt {
    private Product[] shoppingCart = new Product[Storage.MAX_PRODUCTS];
    private double totalPrice = 0.0;
    private boolean isPaid;
    
    //----- Getter -----
    public Product[] getShoppingCart() {
        return this.shoppingCart;
    }
    
    public double getTotalPrice() {
        return this.totalPrice;
    }
    
    public boolean isPaid() {
        return this.isPaid;
    }
    
    //----- Methoden -----
    public void addProductToCart(Product productToAdd, int quantity) {
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
    }
    
    //RemoveFromCart Methode
}