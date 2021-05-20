package de.volkswagen.f73;

import de.volkswagen.f73.utility.ConsoleHandler;
import de.volkswagen.f73.utility.CsvHandler;

public class Main {

	public static void main(String[] args) {
		Customer[] customers = CsvHandler.loadCustomers();
		//CsvHandler.loadCustomers();
		CsvHandler.saveCustomers(customers);
		ConsoleHandler cHandler = new ConsoleHandler();
		cHandler.start();
	}

}
