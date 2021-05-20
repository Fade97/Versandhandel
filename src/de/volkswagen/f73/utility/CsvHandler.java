package de.volkswagen.f73.utility;


import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvHandler {
	
	public static void CsvExport() {
		String fileName = "Export.csv";
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));)
		{
			writer.write("CustomerNr,Firstname,Lastname,Street,Housenr,ZipCode,City");
			writer.newLine();
			writer.close();
		} catch (Exception e) {
			System.out.println("EXCEPTION BEIM EXPORT");
		}
	}
	
	
}
