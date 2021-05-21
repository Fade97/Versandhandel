package de.volkswagen.f73.utility;

import de.volkswagen.f73.Customer;

public class UserManagement {

    private static UserManagement instance;

    private Customer[] customers;
    /**
     * Singleton Pattern
     * @return
     */
    public static UserManagement instance() {
        if (instance == null) {
            instance = new UserManagement();
        }
        return instance;
    }

    private UserManagement() {
        customers = CsvHandler.loadCustomers();
    }

    public boolean save() {
        return CsvHandler.saveCustomers(customers);
    }

    public boolean addUser(Customer c) {
        boolean ret = false;
        if (find(c.getCustomerNr()) == null) {
            ret = true;
            Customer[] tempCustomers;
            if (customers == null) {
                customers = new Customer[1];
                customers[0] = c;
            }else {
                tempCustomers = customers;
                customers = new Customer[tempCustomers.length + 1];
                for (int i = 0; i < tempCustomers.length; i++) {
                    customers[i] = tempCustomers[i];
                }
                customers[tempCustomers.length] = c;
            }
        }
        return ret;
    }

    public Customer getUser(String customerNr) {
        Customer c = null;
        try {
            c = find(Integer.parseInt(customerNr));
        } catch (Exception e) {
        }
        return c;
    }

    private Customer find(int customerNr) {
        Customer retCustomer = null;
        if (customers != null) {
            for (Customer c : this.customers) {
                if (c.getCustomerNr() == customerNr) {
                    retCustomer = c;
                }
            }
        }
        return retCustomer;
    }

}
