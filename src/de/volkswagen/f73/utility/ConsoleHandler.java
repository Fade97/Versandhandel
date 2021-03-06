package de.volkswagen.f73.utility;

import java.text.DecimalFormat;
import java.util.Scanner;

import de.volkswagen.f73.*;
import de.volkswagen.f73.Product.Category;

/**
 * 
 * @author Fabian Duerkop
 */
public class ConsoleHandler {

    private static final int WIDTH = 55;
    private static final int HEIGHT = 12;

    private static final boolean BORDER = true;
    private static final boolean NO_BORDER = false;

    private int productPage;
    private int receiptsPage;
    private Customer customer;
    
    private Scanner sc;

    /**
     * Possible alignment options
     * <p>
     * LEFT, CENTER, RIGHT
     * 
     */
    private enum Alignment {
        LEFT, CENTER, RIGHT;
    }

    public void start() {
        sc = new Scanner(System.in);
        
        printWelcome();
        if (!printLogin()) {
            printRegister();
        }
        String menuAuswahl = "";
        do {
            menuAuswahl = printMenu();
            switch (menuAuswahl) {
            case "0":
                printAccount();
                sc.nextLine();
                
                break;
            case "1":
                productPage = 0;
                String auswahl = "";
                Category category = printCategory();
                while (!auswahl.equals("x")) {
                    auswahl = printProducts(category);
                    if (auswahl.equals("n")) {
                        productPage++;
                    } else if (auswahl.equals("v") && productPage > 0) {
                        productPage--;
                    } else if (auswahl.equals("w")) {
                        productPage = 0;
                        while (!auswahl.equals("x")) {
                            auswahl = printWarenkorb();
                            if (auswahl.equals("n")) {
                                productPage++;
                            } else if (auswahl.equals("v") && productPage > 0) {
                                productPage--;
                            }
                        }
                        auswahl = "";
                    }
                }
                break;
            case "2":
                receiptsPage = 0;
                productPage = 0;
                String auswahlReceipt = "";
                while (!auswahlReceipt.equals("x")) {
                    auswahlReceipt = printReceipts();
                    int receiptNr = 0;
                    try {
                        receiptNr = Integer.parseInt(auswahlReceipt);
                        while (!auswahlReceipt.equals("x")) {
                            auswahlReceipt = printReceiptContent(receiptNr);
                            if (auswahlReceipt.equals("n")) {
                                productPage++;
                            } else if (auswahlReceipt.equals("v") && productPage > 0) {
                                productPage--;
                            }
                        }
                        auswahlReceipt = "";
                    } catch (Exception e) {
                        if (auswahlReceipt.equals("n")) {
                            receiptsPage++;
                        } else if (auswahlReceipt.equals("v") && receiptsPage > 0) {
                            receiptsPage--;
                        }
                    }
                }
                break;

            default:
                break;
            }

        } while (!menuAuswahl.equals("x"));
        sc.close();
    }

    private void printWelcome() {
        String tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                (int) (Math.floor((HEIGHT - 4) / 2.0)));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(stringToConsole("Welcome", Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', 7, Alignment.CENTER, BORDER));

        tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER, (int) (Math.ceil((HEIGHT - 4) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

    }

    private boolean printLogin() {
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                (int) (Math.floor((HEIGHT - 4) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(stringToConsole("Login", Alignment.CENTER, BORDER));

        tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER, (int) (Math.ceil((HEIGHT - 4) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(stringToConsole("r) Registrieren", Alignment.RIGHT, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        System.out.print("Bitte KundenNr eingeben: ");
        String kundenNr = sc.nextLine();
        UserManagement manager = UserManagement.instance();
        Customer c = manager.getUser(kundenNr);
        boolean needsToRegister = false;

        if (kundenNr.equals("r")) {
            needsToRegister = true;
        }

        while (c == null && !needsToRegister) {
            System.out.print("KundenNr nicht vorhanden! Bitte erneut versuchen: ");
            kundenNr = sc.nextLine();
            if (kundenNr.equals("r")) {
                needsToRegister = true;
            }
            manager = UserManagement.instance();
            c = manager.getUser(kundenNr);
        }
        //
        this.customer = c;

        return !needsToRegister;
    }

    private boolean printRegister() {
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                (int) (Math.floor((HEIGHT - 3) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(stringToConsole("Registrieren", Alignment.CENTER, BORDER));

        tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER, (int) (Math.ceil((HEIGHT - 3) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        // public Customer(String firstName, String lastName, String street, String
        // houseNr, String zipCode, String City)

            System.out.println("Bitte Vornamen eingeben:");
            String firstName = sc.nextLine();

            System.out.println("Bitte Nachname eingeben:");
            String lastName = sc.nextLine();

            System.out.println("Bitte Strasse eingeben:");
            String street = sc.nextLine();

            System.out.println("Bitte Hausnummer eingeben:");
            String houseNr = sc.nextLine();

            System.out.println("Bitte Plz eingeben:");
            String zipCode = sc.nextLine();

            System.out.println("Bitte Stadt eingeben:");
            String City = sc.nextLine();

            Customer c = new Customer(firstName, lastName, street, houseNr, zipCode, City);
            UserManagement.instance().addUser(c);
            customer = c;


        return false;
    }

    private String printMenu() {
        /**
         * printAccount printProducts
         * 
         */
        final int staticLines = 7;
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(stringToConsole("Menu", Alignment.CENTER, BORDER));

        System.out.println(stringToConsole("0) Kundenkonto bearbeiten", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("1) Produkte kaufen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("2) Rechnungen anzeigen", Alignment.LEFT, BORDER));

        String tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                (int) (Math.floor((HEIGHT - staticLines) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }
        tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                (int) (Math.ceil((HEIGHT - staticLines) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(stringToConsole("x) beenden", Alignment.RIGHT, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String auswahl = "";
        while (!auswahl.equals("x") && !auswahl.equals("0") && !auswahl.equals("1") && !auswahl.equals("2")) {
            auswahl = sc.nextLine();
        }
        
        return auswahl;
    }

    private void printAccount() {
        // printEditAccount
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String[] customerInfo = customer.print();
        for (String s : customerInfo) {
            System.out.println(stringToConsole(s, Alignment.LEFT, BORDER));

        }

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
    }

    private void printEditAccount() {
        
    }

    private String printProducts(Category category) {
        // Test
        Product[] products = Storage.getProducts(category);

        Receipt[] receipts = customer.getReceipts();
        Receipt receipt = null;
        if (receipts != null) {
            for (Receipt r : receipts) {
                if (!r.isPaid()) {
                    receipt = r;
                    break;
                }
            }
        }
        if (receipt == null) {
            receipt = new Receipt();
            customer.addReceipt(receipt);
        }

        int iProductCnt = receipt.getNumberOfItems();
        double dValue = receipt.getTotalPrice();
        // Test end
        String retCommand = "";
        DecimalFormat df = new DecimalFormat("#.##");
        int staticLines = 6;
        int productsPerPage = (HEIGHT - staticLines);

        while (products.length - ((productPage) * productsPerPage) <= 0) {
            if (productPage <= 0) {
                break;
            }
            productPage--;
        }

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        System.out.println(stringToConsole("Produkte", Alignment.CENTER, BORDER));

        // Products
        for (int i = 0; i < (HEIGHT - staticLines) && i + productPage * productsPerPage < products.length; i++) {
            Product curP = products[i + productPage * (HEIGHT - staticLines)];
            double grossPrice = Receipt.calculateGrossPrice(curP.getPrice(), curP.getTax());
            String left = "" + i + ") " + curP.getName();
            String right = df.format(grossPrice) + "\u20AC";
            System.out.println(stringToConsole(left + addPadding(left.length(), right.length(), BORDER) + right,
                    Alignment.LEFT, BORDER));
        }

        if (HEIGHT - staticLines - (products.length - productsPerPage * productPage) > 0) {
            System.out.println(wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                    HEIGHT - staticLines - (products.length - productsPerPage * productPage)));
        }

        // Footer
        System.out.println(wholeLine('-', 7, Alignment.RIGHT, BORDER));
        String sLeft = "w) Warenkorb " + iProductCnt + " Artikel";
        String sRight = df.format(dValue) + "\u20AC";
        System.out.println(stringToConsole(sLeft + addPadding(sLeft.length(), sRight.length(), BORDER) + sRight,
                Alignment.CENTER, BORDER));

        sLeft = "Seite " + (productPage + 1) + "/" + ((int) Math.ceil(products.length / (HEIGHT - staticLines * 1.0)));
        sRight = "";
        if (productPage + 1 != 1) {
            sRight += "v) vorherige";
        }
        if (((int) Math.ceil(products.length / (HEIGHT - staticLines * 1.0))) != productPage + 1) {
            sRight += " n) n\u00e4chste Seite";
        }

        sRight += "  x) zur\u00fcck";
        System.out.println(stringToConsole(sLeft + addPadding(sLeft.length(), sRight.length(), BORDER) + sRight,
                Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        // Product selection
        retCommand = sc.nextLine();
        try {
            int selectedIndex = Integer.parseInt(retCommand);
            if (selectedIndex + productPage * productsPerPage < products.length && selectedIndex < productsPerPage) {
                Product p = products[selectedIndex + productPage * productsPerPage];
                receipt.addProductToCart(p, 1);
            }
        } catch (Exception e) {

        }
        
        return retCommand;
    }

    private String printWarenkorb() {
        Receipt[] receipts = customer.getReceipts();
        Receipt receipt = null;
        if (receipts != null) {
            for (Receipt r : receipts) {
                if (!r.isPaid()) {
                    receipt = r;
                    break;
                }
            }
        }
        if (receipt == null) {
            receipt = new Receipt();
            customer.addReceipt(receipt);
        }
        Product[] tempProducts = receipt.getShoppingCart();
        int productCount = 0;
        for (int i = 0; i < tempProducts.length; i++) {
            if (tempProducts[i] != null) {
                productCount++;
            }
        }
        Product[] products = new Product[productCount];
        int j = 0;
        for (int i = 0; i < tempProducts.length; i++) {
            if (tempProducts[i] != null) {
                products[j] = tempProducts[i];
                j++;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        int staticLines = 6;
        int productsPerPage = (HEIGHT - staticLines);

        while (products.length - ((productPage) * productsPerPage) <= 0) {
            if (productPage <= 0) {
                break;
            }
            productPage--;
        }

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(stringToConsole("Warenkorb", Alignment.CENTER, BORDER));

        // Products
        for (int i = 0; i < (HEIGHT - staticLines) && i + productPage * productsPerPage < products.length; i++) {
            Product thisProduct = products[i + productPage * (HEIGHT - staticLines)];
            String left = "" + i + ") " + thisProduct.getName() + " x" + thisProduct.getQuantity();
            String right = df.format(Receipt.calculateGrossPrice(thisProduct.getPrice() * thisProduct.getQuantity(),
                    thisProduct.getTax())) + "\u20AC";
            System.out.println(stringToConsole(left + addPadding(left.length(), right.length(), BORDER) + right,
                    Alignment.LEFT, BORDER));
        }

        if (HEIGHT - staticLines - (products.length - productsPerPage * productPage) > 0) {
            System.out.println(wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                    HEIGHT - staticLines - (products.length - productsPerPage * productPage)));
        }

        // Footer
        System.out.println(wholeLine('-', 7, Alignment.RIGHT, BORDER));

        System.out.println(stringToConsole(df.format(receipt.getTotalPrice()) + "\u20AC", Alignment.RIGHT, BORDER));

        String sLeft = "Seite " + (productPage + 1) + "/"
                + ((int) Math.ceil(products.length / (HEIGHT - staticLines * 1.0)));
        String sRight = "";
        if (productPage + 1 != 1) {
            sRight += "v) vorherige";
        }
        if (((int) Math.ceil(products.length / (HEIGHT - staticLines * 1.0))) != productPage + 1) {
            sRight += " n) n\u00e4chste Seite";
        }

        if (receipt.getNumberOfItems() > 0) {
            sRight += " k) kaufen";
        }
        sRight += " x) zur\u00fcck";
        System.out.println(stringToConsole(sLeft + addPadding(sLeft.length(), sRight.length(), BORDER) + sRight,
                Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String retCommand = sc.nextLine();
        
        try {
            int selectedIndex = Integer.parseInt(retCommand);
            if (selectedIndex + productPage * productsPerPage < products.length && selectedIndex < productsPerPage) {
                Product p = products[selectedIndex + productPage * productsPerPage];
                System.out.println(p.getName() + " - 1");
                receipt.removeProductFromCart(p, 1);
            }
        } catch (Exception e) {

        }
        if (retCommand.equals("k")) {
            if (receipt.getTotalPrice() > 0.0) {
                receipt.setPaid(true);
                System.out.println("Rechnung mit der Nummer: " + receipt.getReceiptNr() + " wurde erstellt.");
            }
            retCommand = "x";
        }

        return retCommand;
    }

    private String printReceipts() {
        Receipt[] receipts = customer.getReceipts();

        DecimalFormat df = new DecimalFormat("#.##");
        int staticLines = 4;
        int receiptsPerPage = (HEIGHT - staticLines);
        if (receipts != null) {
            Receipt[] tempReceipts = receipts;
            int receiptCount = 0;
            for (int i = 0; i < tempReceipts.length; i++) {
                if (tempReceipts[i].isPaid()) {
                    receiptCount++;
                }
            }
            receipts = new Receipt[receiptCount];
            int j = 0;
            for (int i = 0; i < tempReceipts.length; i++) {
                if (tempReceipts[i].isPaid()) {
                    receipts[j] = tempReceipts[i];
                    j++;
                }
            }

            while (receipts.length - ((receiptsPage) * receiptsPerPage) <= 0) {
                if (receiptsPage <= 0) {
                    break;
                }
                receiptsPage--;
            }
        }

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(stringToConsole("Rechnungen", Alignment.CENTER, BORDER));

        if (receipts != null) {
            // Receipts
            for (int i = 0; i < (HEIGHT - staticLines) && i + receiptsPage * receiptsPerPage < receipts.length; i++) {
                Receipt thisReceipt = receipts[i + receiptsPage * (HEIGHT - staticLines)];
                String left = "" + i + ") " + thisReceipt.getReceiptNr();
                String right = df.format(thisReceipt.getTotalPrice()) + "\u20AC";
                System.out.println(stringToConsole(left + addPadding(left.length(), right.length(), BORDER) + right,
                        Alignment.LEFT, BORDER));
            }

            if (HEIGHT - staticLines - (receipts.length - receiptsPerPage * receiptsPage) > 0) {
                System.out.println(wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                        HEIGHT - staticLines - (receipts.length - receiptsPerPage * receiptsPage)));
            }
        } else {
            if (HEIGHT - staticLines - (receiptsPerPage * receiptsPage) > 0) {
                System.out.println(wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                        HEIGHT - staticLines - (receiptsPerPage * receiptsPage)));
            }
        }
        // Footer
        String sLeft = "";
        String sRight = "";
        if (receipts != null) {
            sLeft = "Seite " + (receiptsPage + 1) + "/"
                    + ((int) Math.ceil(receipts.length / (HEIGHT - staticLines * 1.0)));
            sRight = "";
            if (receiptsPage + 1 != 1) {
                sRight += "v) vorherige";
            }
            if (((int) Math.ceil(receipts.length / (HEIGHT - staticLines * 1.0))) != receiptsPage + 1) {
                sRight += " n) n\u00e4chste Seite";
            }
        }

        sRight += " x) zur\u00fcck";
        System.out.println(stringToConsole(sLeft + addPadding(sLeft.length(), sRight.length(), BORDER) + sRight,
                Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String retCommand = sc.nextLine();
        

        int selectedReceiptNr = 0;
        try {
            Receipt thisReceipt = receipts[receiptsPerPage * receiptsPage + Integer.parseInt(retCommand)];
            if(thisReceipt != null) {
                selectedReceiptNr = thisReceipt.getReceiptNr();
                
            }
            retCommand = Integer.toString(selectedReceiptNr);
        } catch (Exception e) {

        }
        return retCommand;
    }

    private String printReceiptContent(int receiptNr) {
        Receipt[] receipts = customer.getReceipts();
        Receipt receipt = null;
        if (receipts != null) {
            for (Receipt r : receipts) {
                if (r.getReceiptNr() == receiptNr && r.isPaid()) {
                    receipt = r;
                    break;
                }
            }
        }
        if (receipt == null) {
            return "x";
        }

        Product[] tempProducts = receipt.getShoppingCart();
        int productCount = 0;
        for (int i = 0; i < tempProducts.length; i++) {
            if (tempProducts[i] != null) {
                productCount++;
            }
        }
        Product[] products = new Product[productCount];
        int j = 0;
        for (int i = 0; i < tempProducts.length; i++) {
            if (tempProducts[i] != null) {
                products[j] = tempProducts[i];
                j++;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        int staticLines = 6;
        int productsPerPage = (HEIGHT - staticLines);

        while (products.length - ((productPage) * productsPerPage) <= 0) {
            if (productPage <= 0) {
                break;
            }
            productPage--;
        }

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(stringToConsole("Rechnung " + receiptNr, Alignment.CENTER, BORDER));

        // Products
        for (int i = 0; i < (HEIGHT - staticLines) && i + productPage * productsPerPage < products.length; i++) {
            Product thisProduct = products[i + productPage * (HEIGHT - staticLines)];
            String left = "" + i + ") " + thisProduct.getName();
            String right = thisProduct.getQuantity() + "x " + df.format(Receipt
                    .calculateGrossPrice(thisProduct.getPrice() * thisProduct.getQuantity(), thisProduct.getTax()))
                    + "\u20AC";
            System.out.println(stringToConsole(left + addPadding(left.length(), right.length(), BORDER) + right,
                    Alignment.LEFT, BORDER));
        }

        if (HEIGHT - staticLines - (products.length - productsPerPage * productPage) > 0) {
            System.out.println(wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                    HEIGHT - staticLines - (products.length - productsPerPage * productPage)));
        }

        // Footer
        System.out.println(wholeLine('-', 7, Alignment.RIGHT, BORDER));

        System.out.println(stringToConsole(df.format(receipt.getTotalPrice()) + "\u20AC", Alignment.RIGHT, BORDER));

        String sLeft = "Seite " + (productPage + 1) + "/"
                + ((int) Math.ceil(products.length / (HEIGHT - staticLines * 1.0)));
        String sRight = "";
        if (productPage + 1 != 1) {
            sRight += "v) vorherige";
        }

        if (((int) Math.ceil(products.length / (HEIGHT - staticLines * 1.0))) != productPage + 1) {
            sRight += " n) n\u00e4chste Seite";
        }

        sRight += " x) zur\u00fcck";
        System.out.println(stringToConsole(sLeft + addPadding(sLeft.length(), sRight.length(), BORDER) + sRight,
                Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String retCommand = sc.nextLine();
        

        return retCommand;
    }

    private Category printCategory() {
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(stringToConsole("Kategorien", Alignment.CENTER, BORDER));

        System.out.println(stringToConsole("0) Alle Produkte anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("1) Obst anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("2) Gem?se anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("3) Fleisch anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("4) Fisch anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("5) Milchprodukte anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("6) Brot anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("7) Getr?nke anzeigen", Alignment.LEFT, BORDER));
        System.out.println(stringToConsole("8) Non-Food anzeigen", Alignment.LEFT, BORDER));

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String auswahl = "";
        while (!auswahl.equals("0") && !auswahl.equals("1") && !auswahl.equals("2") && !auswahl.equals("3")
                && !auswahl.equals("4") && !auswahl.equals("5") && !auswahl.equals("6") && !auswahl.equals("7")
                && !auswahl.equals("8")) {
            auswahl = sc.nextLine();
        }
        
        Category categoryToReturn = Category.NO_CATEGORY;
        switch (auswahl) {
        case "0":
            categoryToReturn = Category.NO_CATEGORY;
            break;
        case "1":
            categoryToReturn = Category.FRUITS;
            break;
        case "2":
            categoryToReturn = Category.VEGETABLES;
            break;
        case "3":
            categoryToReturn = Category.MEAT;
            break;
        case "4":
            categoryToReturn = Category.FISH;
            break;
        case "5":
            categoryToReturn = Category.MILK_PRODUCTS;
            break;
        case "6":
            categoryToReturn = Category.BREAD;
            break;
        case "7":
            categoryToReturn = Category.DRINKS;
            break;
        case "8":
            categoryToReturn = Category.NON_FOOD;
            break;
        }
        return categoryToReturn;
    }

    /**
     * Returns a whole line in the console using the char c
     * 
     * @param c          - the char to fill the line with
     * @param length     - the length to fill; must be smaller or equal to the max
     *                   size WIDTH
     * @param align      - Alignment parameter
     * @param hasBorders - print the '|' char on both sides
     * @return - a String containing the line
     * @see #WIDTH
     * @see Alignment
     */
    private String wholeLine(char c, int length, Alignment align, boolean hasBorders) {
        String retText = "";

        String text = "";
        for (int i = 0; i < length; i++) {
            text += c;
        }
        retText = stringToConsole(text, align, hasBorders);

        return retText;
    }

    /**
     * Returns multiCnt times a whole line in the console using the char c
     * 
     * @param c          - the char to fill the line with
     * @param length     - the length to fill; must be smaller or equal to the max
     *                   size WIDTH
     * @param align      - Alignment parameter
     * @param hasBorders - print the '|' char on both sides
     * @param multiCnt   - amount of lines desired
     * @return - a String containing the lines
     * @see #wholeLine(char, int, Alignment, boolean)
     */
    private String wholeLineMulti(char c, int length, Alignment align, boolean hasBorders, int multiCnt) {
        String retText = "";
        for (int i = 0; i < multiCnt; i++) {
            retText += wholeLine(c, length, align, hasBorders);
            if (i != multiCnt - 1) {
                retText += "\n";
            }
        }
        return retText;
    }

    /**
     * Convert your desired text to a formatted console line
     * 
     * @param text      - your desired text
     * @param align     - Alignment parameter
     * @param hasBorder - print the '|' char on both sides
     * @return - a String containing the formatted line
     * @see Alignment
     */
    private String stringToConsole(String text, Alignment align, boolean hasBorder) {
        String retText = "";
        int textLength = text.length();
        int maxLength = WIDTH - 2;
        if (!hasBorder) {
            maxLength = WIDTH;
        }

        if (textLength <= maxLength) {
            int lengthToFill = (maxLength) - textLength;
            if (hasBorder)
                retText += "|";
            switch (align) {
            case LEFT:
                retText += text;
                for (int i = 0; i < lengthToFill; i++) {
                    retText += " ";
                }
                break;

            case CENTER:
                for (int i = 0; i < Math.ceil(lengthToFill / 2.); i++) {
                    retText += " ";
                }
                retText += text;
                for (int i = 0; i < lengthToFill / 2; i++) {
                    retText += " ";
                }
                break;

            case RIGHT:
                for (int i = 0; i < lengthToFill; i++) {
                    retText += " ";
                }
                retText += text;
                break;
            }
            if (hasBorder)
                retText += "|";
        }

        return retText;
    }

    /**
     * Returns a String filled with the desired amount of white spaces
     * @param amountOfPadding - amount of white spaces to add
     * @return String containing the padding
     */
    private String addPadding(int amountOfPadding) {
        String retText = "";
        for (int i = 0; i < amountOfPadding; i++) {
            retText += " ";
        }
        return retText;
    }

    /**
     * Returns a String filled with white spaces. The amount matches the difference between the total {@link WIDTH} allowed and the supplied character counts
     * @param leftCharCnt - amount of chars on the left side of the padding
     * @param rightCharCnt - amount of chars on the right side of the padding
     * @param hasBorder - whether the line needs to have a border
     * @return String containing the padding
     */
    private String addPadding(int leftCharCnt, int rightCharCnt, boolean hasBorder) {
        String retText = "";
        int cnt = WIDTH - rightCharCnt - leftCharCnt;
        if (hasBorder) {
            cnt = WIDTH - 2 - rightCharCnt - leftCharCnt;
        }
        for (int i = 0; i < cnt; i++) {
            retText += " ";
        }
        return retText;
    }

    private void printError(String errorMessage) {
        System.err.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        String tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER,
                (int) (Math.floor((HEIGHT - 3) / 2.0)));
        if (!tempText.isEmpty()) {
            System.err.println(tempText);
        }

        System.err.println(stringToConsole(errorMessage, Alignment.CENTER, BORDER));

        tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER, (int) (Math.ceil((HEIGHT - 3) / 2.0)));
        if (!tempText.isEmpty()) {
            System.err.println(tempText);
        }

        System.err.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
    }
}
