package de.volkswagen.f73.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.volkswagen.f73.Customer;
import de.volkswagen.f73.Product;
import de.volkswagen.f73.Receipt;
import de.volkswagen.f73.Storage;

public class CsvHandler {
    static String fileName = "Export.csv";
    static String fileNameReceipt = "Receipts.csv";
    static final String delimiter = ";";

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
        if (customerCount <= 0) {
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
                String[] tmp = textLine.split(delimiter);
                // "CustomerNr,Firstname,Lastname,Street,Housenr,ZipCode,City"
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
            String tableHeader = "CustomerNr;Firstname;Lastname;Street;Housenr;ZipCode;City";
            writer.write(tableHeader);
            writer.newLine();
            for (int i = 0; i < customers.length; i++) {
                String oneCustomer = (String.valueOf(customers[i].getCustomerNr()) + delimiter
                        + customers[i].getFirstName() + delimiter + customers[i].getLastName() + delimiter
                        + customers[i].getAddress().getStreet() + delimiter + customers[i].getAddress().getHouseNr()
                        + delimiter + customers[i].getAddress().getZipCode() + delimiter
                        + customers[i].getAddress().getCity());
                writer.write(oneCustomer);
                writer.newLine();
            }

            System.out.println("Export: " + fileName + " wurde erstellt");
            writer.close();

        } catch (Exception e) {
            System.err.println("Exception beim Speichern eingetreten!");
            e.printStackTrace();
            fileIsSaved = false;

        }
        return fileIsSaved;
    }

    public static boolean saveReceipt(Customer[] customers) {
        boolean fileIsSaved = true;
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileNameReceipt));) {
            String tableHeader = "ReceiptNr;CustomerNr;InventoryNr;Quantity;netPrice;Tax;grossPrice";
            writer.write(tableHeader);
            writer.newLine();
            for (int i = 0; i < customers.length; i++) { // Shleife Customer
                Receipt[] receipts = customers[i].getReceipts();
                if (receipts != null) {
                    for (int n = 0; n < receipts.length; n++) { // Schleife Receipt
                        Product[] shoppingCart = receipts[n].getShoppingCart();
                        for (int j = 0; j < shoppingCart.length; j++) { // Schleife Cart
                            if (shoppingCart[j] != null) {
                                Product thisCart = customers[i].getReceipts()[n].getShoppingCart()[j];
                                String oneLineOfOneRecipe = (String
                                        .valueOf(customers[i].getReceipts()[n].getReceiptNr()) + delimiter
                                        + String.valueOf(customers[i].getCustomerNr()) + delimiter
                                        + String.valueOf(thisCart.getInventoryNr()) + delimiter
                                        + String.valueOf(thisCart.getQuantity()) + delimiter
                                        + String.valueOf(
                                                (thisCart.getPrice()) * thisCart.getQuantity()).replace('.', ',')
                                        + delimiter + thisCart.getTax() + delimiter
                                        + String.valueOf(
                                                Receipt.calculateGrossPrice(thisCart.getPrice(), thisCart.getTax())
                                                        * thisCart.getQuantity())
                                                .replace('.', ','));
                                writer.write(oneLineOfOneRecipe);
                                writer.newLine();
                            }
                        }
                    }
                }
            }
            System.out.println("Export: " + fileNameReceipt + " wurde erstellt");
            writer.close();

        } catch (Exception e) {
            System.err.println("Exception beim Speichern eingetreten!");
            e.printStackTrace();
            fileIsSaved = false;
        }

        return false;
    }

    public static Receipt[] loadReceipts() {

        BufferedReader reader;
        int receiptCount = 0;
        try {
            reader = new BufferedReader(new FileReader(Paths.get(fileNameReceipt).toString()));
            int lines = 0;
            while (reader.readLine() != null)
                lines++;
            receiptCount = lines - 1;
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (receiptCount <= 0) {
            return null;
        }
        Receipt[] receipts = new Receipt[receiptCount];
        try {
            reader = new BufferedReader(new FileReader(Paths.get(fileNameReceipt).toString()));
            StringBuilder textRead = new StringBuilder();
            String textLine = reader.readLine();

            for (int i = 0; i < receipts.length; i++) {
                textRead.append(System.lineSeparator());
                textLine = reader.readLine();
                String[] tmp = textLine.split(delimiter);
                Customer customer = UserManagement.instance().getUser(tmp[1]);
                if (customer != null) {
                    Receipt[] customerReceipts = customer.getReceipts();
                    Receipt thisReceipt = null;
                    if (customerReceipts != null) {
                        for (Receipt n : customerReceipts) {
                            if (n.getReceiptNr() == Integer.parseInt(tmp[0])) {
                                thisReceipt = n;
                                break;
                            }
                        }
                    }
                    Product[] products = Storage.getProducts();
                    Product thisProduct = null;
                    if (products != null) {
                        for (Product p : products) {
                            if (p.getInventoryNr() == Integer.parseInt(tmp[2])) {
                                thisProduct = new Product(p);
                                break;
                            }
                        }
                    }
                    if (thisReceipt == null) {
                        thisReceipt = new Receipt(Integer.parseInt(tmp[0]));
                        customer.addReceipt(thisReceipt);
                    }
                    if (thisProduct != null) {
                        thisProduct.setPrice(Double.parseDouble(tmp[4].replace(',', '.')) / Integer.parseInt(tmp[3]));
                        thisReceipt.addProductToCart(thisProduct, Integer.parseInt(tmp[3]));
                        thisReceipt.setPaid(true);
                    }
                }
                // "ReceiptNr;CustomerNr;InventoryNr;Quantity;netPrice;Tax;grossPrice"
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
