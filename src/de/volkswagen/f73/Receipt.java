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
                
                //Auf menge überprüfen ob genug vorhanden
                if (Storage.getStock() - quantity >= 0) {
                    shoppingCart[i] = productToAdd;
                    shoppingCart[i].setQuantity(quantity); 
                    Storage.setStock(productToAdd, quantity);
                }
                
            } else if (shoppingCart[i].getInventoryNr() == productToAdd.getInventoryNr()) {
                shoppingCart[i].setQuantity(shoppingCart[i].getQuantity() + quantity);
            }
            
        }
    }
    
    //RemoveFromCart Methode
    //Bei menge = 0 stock = null
}