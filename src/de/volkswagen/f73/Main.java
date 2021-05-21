package de.volkswagen.f73;

import de.volkswagen.f73.utility.*;

public class Main {

	public static void main(String[] args) {
		UserManagement.instance();
	    //UserManagement.instance().addUser( new Customer("Erik", "Garcia", "Wollgrasweg", "18", "38446", "Wolfsburg") );
		ConsoleHandler cHandler = new ConsoleHandler();
		cHandler.start();
		UserManagement.instance().save();
	}

}
