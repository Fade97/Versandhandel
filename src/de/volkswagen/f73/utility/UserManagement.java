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
    
    public boolean addUser(Customer c) {
        boolean ret = false;
        if(find(c.getCustomerNr()) != null) {
            ret = true;
            Customer[] tempCustomers = customers;
            customers = new Customer[tempCustomers.length + 1];
            for(int i = 0; i < tempCustomers.length; i++) {
                customers[i] = tempCustomers[i];
            }
            customers[tempCustomers.length] = c;
        }
        return ret;
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
