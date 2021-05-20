package de.volkswagen.f73;

import de.volkswagen.f73.utility.ConsoleHandler;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConsoleHandler cHandler = new ConsoleHandler();
		cHandler.start();
		//Erik
		Customer newCustomer = new Customer("Erik", "Garcia", "Wollgrasweg", "18", "38446", "Wolfsburg", 123456);
	}

}
