package de.volkswagen.f73;

public class Customer {
	private String firstName;
	private String lastName;
	private int customerNr;
	private Address address = new Address();

	private Receipt[] receipts;

	// Constructor
	public Customer(String firstName, String lastName, String street, String houseNr, String zipCode, String City,
			int customerNr) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address.setStreet(street);
		this.address.setHouseNr(houseNr);
		this.address.setZipCode(customerNr);
		this.address.setCity(City);
		this.customerNr = customerNr;
	}

	public String[] print() {
		
		String[] CustomerDetails = { this.getFirstName(), this.getLastName(), this.getAddress().getStreet(), this.address.getHouseNr(), String.valueOf(this.address.getZipCode()), this.address.getCity(), String.valueOf(this.getCustomerNr()) };

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

}
