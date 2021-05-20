package de.volkswagen.f73;

public class Receipt {
    private Product[] shoppingCart = new Product[Storage.MAX_PRODUCTS];
    private double totalPrice = 0.0;
    
    //----- Getter -----
    public Product[] getShoppingCart() {
        return this.shoppingCart;
    }
    
    //----- Methoden -----
    public void addProductToCart(Product productToAdd, int quantity) {
        for (int i = 0; i < shoppingCart.length; i++) {
            
            if (shoppingCart[i] == null) {
                shoppingCart[i] = productToAdd;
                shoppingCart[i].setStock(quantity); //Auf menge überprüfen ob genug vorhanden
            } else if (shoppingCart[i].getInventoryNr() == productToAdd.getInventoryNr()) {
                shoppingCart[i].setStock(shoppingCart[i].getStock() + quantity);
            }
            
        }
    }
    
    //RemoveFromCart Methode
    //Bei menge = 0 stock = null
}