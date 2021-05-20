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
	
	public static void CsvExport() {
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));)
		{
			writer.write("CustomerNr,Firstname,Lastname,Street,Housenr,ZipCode,City");
			writer.newLine();
			writer.close();
		} catch (Exception e) {
			System.out.println("EXCEPTION BEIM EXPORT");
		}
	}
	
	public static Customer[] loadCustomers() {
	    BufferedReader reader;
        int customerCount = 0;
	    try {
            reader = new BufferedReader(new FileReader(Paths.get(fileName).toString()));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            customerCount = lines -1;
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    Customer[] customers = new Customer[customerCount];
	    try {
            reader = new BufferedReader(new FileReader(Paths.get(fileName).toString()));
            
            // line.split(',')[0] -> Vorname
            // Customer[i] = new Customer(Vorname,....)
            
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    return null;
	}
	
	
}
