package de.volkswagen.f73;

public class Customer {
    private String firstName;
    private String lastName;
    private int customerNr;
    private static int CUSTOMER_CNT = 0;
    private Address address = new Address();

    private Receipt[] receipts;

    // Constructor
    public Customer(String firstName, String lastName, String street, String houseNr, String zipCode, String City) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address.setStreet(street);
        this.address.setHouseNr(houseNr);
        this.address.setZipCode(Integer.parseInt(zipCode));
        this.address.setCity(City);
        this.customerNr = 1000 + CUSTOMER_CNT++;
    }

    public String[] print() {

        String[] CustomerDetails = { this.getFirstName() + " " + this.getLastName(),
                this.getAddress().getStreet() + " " + this.address.getHouseNr(),
                String.valueOf(this.address.getZipCode()) + " " + this.address.getCity(),
                String.valueOf(this.getCustomerNr()) };

        return CustomerDetails;
    }

    // Getter & Setter
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getCustomerNr() {
        return customerNr;
    }

    public Receipt[] getReceipts() {
        return receipts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addReceipt(Receipt r) {
        if (r != null) {
            if (this.receipts == null) {
                this.receipts = new Receipt[1];
                this.receipts[0] = r;
            } else {
                Receipt[] rTemps = this.receipts;
                this.receipts = new Receipt[rTemps.length+1];
                for(int i = 0; i < rTemps.length; i++) {
                    this.receipts[i] = rTemps[i];
                }
                this.receipts[rTemps.length] = r;
            }
        }
    }

}
