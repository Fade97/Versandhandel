package de.volkswagen.f73;

import de.volkswagen.f73.utility.*;

public class Main {

	public static void main(String[] args) {
	    CsvHandler.loadProducts();
	    UserManagement.instance();
		ConsoleHandler cHandler = new ConsoleHandler();
		cHandler.start();
		UserManagement.instance().save();
	}

}
