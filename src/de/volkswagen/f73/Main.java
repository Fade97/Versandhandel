package de.volkswagen.f73;

import de.volkswagen.f73.utility.ConsoleHandler;
import de.volkswagen.f73.utility.CsvHandler;
import de.volkswagen.f73.utility.UserManagement;

public class Main {

	public static void main(String[] args) {
		Customer[] customers = CsvHandler.loadCustomers();
		//CsvHandler.loadCustomers();
		CsvHandler.saveCustomers(customers);
		ConsoleHandler cHandler = new ConsoleHandler();
		UserManagement.instance().addUser( new Customer("Erik", "Garcia", "Wollgrasweg", "18", "38446", "Wolfsburg") );
		cHandler.start();
	}

}
