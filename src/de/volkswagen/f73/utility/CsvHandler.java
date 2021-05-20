package de.volkswagen.f73.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.volkswagen.f73.Customer;

public class CsvHandler {
	static String fileName = "Export.csv";

	public static Customer[] loadCustomers() {
		BufferedReader reader;
		int customerCount = 0;
		try {
			reader = new BufferedReader(new FileReader(Paths.get(fileName).toString()));
			int lines = 0;
			while (reader.readLine() != null)
				lines++;
			customerCount = lines - 1;
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if( customerCount <= 0) {
		    return null;
		}
		Customer[] customers = new Customer[customerCount];
		try {
			reader = new BufferedReader(new FileReader(Paths.get(fileName).toString()));
			StringBuilder textRead = new StringBuilder();
			String textLine = reader.readLine();

					for (int i = 0; i < customers.length; i++) {
						textRead.append(System.lineSeparator());
						textLine = reader.readLine();
						String[] tmp = textLine.split(",");
						//"CustomerNr,Firstname,Lastname,Street,Housenr,ZipCode,City"
						customers[i] = new Customer(tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], tmp[0]);
				}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return customers;
	}

	public static boolean saveCustomers(Customer[] customers) {
		boolean fileIsSaved = true;
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));) {
				String tableHead = "CustomerNr,Firstname,Lastname,Street,Housenr,ZipCode,City";
				writer.write(tableHead);
				writer.newLine();
			for (int i = 0 ; i < customers.length ; i++) {
				String oneCustomer = (String.valueOf(customers[i].getCustomerNr()) + "," + customers[i].getFirstName() + "," + customers[i].getLastName() + "," + customers[i].getAddress().getStreet() + "," + customers[i].getAddress().getHouseNr() + "," + customers[i].getAddress().getZipCode() + "," + customers[i].getAddress().getCity());
				writer.write(oneCustomer);
				writer.newLine();
			}
			
			System.out.println("Export: " + fileName + " wurde erstellt");
			writer.close();

		} catch (Exception e) {
			System.out.println("Expection beim Speichern eingetreten!");
			fileIsSaved = false;
			
		}
		return fileIsSaved;
	}

}
