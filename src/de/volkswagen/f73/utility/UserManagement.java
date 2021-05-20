package de.volkswagen.f73.utility;

import de.volkswagen.f73.Customer;

public class UserManagement {
    
    private Customer[] customers;
    
    public UserManagement() {
        customers = CsvHandler.loadCustomers();
    }
    
    public boolean save() {
        // TODO implement save method using CsvHandler
        //return CsvHandler.saveCustomers(customers);
        return false;
    }
    
    public Customer getUser(int customerNr) {
        return find(customerNr);
    }
    
    private Customer find(int customerNr) {
        Customer retCustomer = null;
        for(Customer c : customers) {
            if(c.getCustomerNr() == customerNr) {
                retCustomer = c;
            }
        }
        return retCustomer;
    }
    
}
