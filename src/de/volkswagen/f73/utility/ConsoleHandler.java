package de.volkswagen.f73.utility;

import java.io.IOException;
import java.util.Scanner;

import de.volkswagen.f73.*;

/**
 * 
 * @author Fabian Duerkop
 */
public class ConsoleHandler {

    private static int WIDTH = 55;
    private static int HEIGHT = 10;

    private static boolean BORDER = true;
    private static boolean NO_BORDER = false;

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
        // Willkommen
        // Login / Register
        // while
        // Produkte anzeigen
        // Produkt ausw�hlen
        Scanner sc = new Scanner(System.in);
        String auswahl = "";

        
        printWelcome();
        printAccount();
        sc.nextLine();

        printLogin();
        String kundenNr = sc.nextLine();
        System.err.println(kundenNr);
        int page = 0;
        while (!auswahl.equals("x")) {
            printProducts(page);
            auswahl = sc.nextLine();
            if (auswahl.equals("n")) {
                page++;
            } else if (auswahl.equals("v") && page > 0) {
                page--;
            }
        }
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
                (int) (Math.floor((HEIGHT - 3) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(stringToConsole("Login", Alignment.CENTER, BORDER));

        tempText = wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER, (int) (Math.ceil((HEIGHT - 3) / 2.0)));
        if (!tempText.isEmpty()) {
            System.out.println(tempText);
        }

        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        System.out.print("Bitte KundenNr eingeben: ");

        return false;
    }

    private boolean printRegister() {
        return false;
    }

    private void printMenu() {
        /**
         * printAccount printProducts
         * 
         */
    }

    private void printAccount() {
        // printEditAccount
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        
        Customer c = new Customer("Fabian", "Duerkop", "abcstrasse", "14", "38554", "Weyhausen");
        String[] customerInfo = c.print();
        for(String s : customerInfo) {
            System.out.println(stringToConsole(s, Alignment.LEFT, BORDER));
            
        }
        
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
    }

    private void printEditAccount() {

    }

    /**
     * Produkte _______________________________ 1) Banane 2) Erdbeere . . .
     * Warenkorb: 4 Artikel | 12.30� Seite 1/10 x: n�chste Seite
     */

    private void printProducts(int page) {
        // Test
        Product[] products = Storage.getProducts();
        
        int iProductCnt = 4;
        double dValue = 12.30;
        // Test end

        int staticLines = 5;
        int productsPerPage = (HEIGHT - staticLines);
        
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

        System.out.println(stringToConsole("Produkte", Alignment.CENTER, BORDER));

        
        for (int i = 0; i < (HEIGHT - staticLines) && i + page * productsPerPage < products.length; i++) {
            String left = "" + i + ") " + products[i + page * (HEIGHT - staticLines)].getName();
            String right = products[i + page * (HEIGHT - staticLines)].getPrice() + "\u20AC";
            System.out.println(stringToConsole(left + addPadding(left.length(), right.length(), BORDER) + right, Alignment.LEFT, BORDER));
        }

        if (HEIGHT - staticLines - products.length > 0) {
            System.out.println(
                    wholeLineMulti(' ', WIDTH - 2, Alignment.CENTER, BORDER, HEIGHT - staticLines - products.length));
        }

        String sLeft = "Warenkorb  " + iProductCnt + " Artikel";
        String sRight = dValue + "\u20AC";
        System.out.println(stringToConsole(sLeft + addPadding(sLeft.length(), sRight.length(), BORDER) + sRight,
                Alignment.CENTER, BORDER));

        sLeft = "Seite " + (page + 1) + "/" + ((int) Math.ceil(products.length / (HEIGHT - staticLines * 1.0)));
        sRight = "v) vorherige n) n\u00e4chste Seite  x) zur\u00fcck";
        System.out.println(stringToConsole(sLeft + addPadding(sLeft.length(), sRight.length(), BORDER) + sRight,
                Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
    }

    private void printReceipt() {

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

    private String addPadding(int amountOfPadding) {
        String retText = "";
        for (int i = 0; i < amountOfPadding; i++) {
            retText += " ";
        }
        return retText;
    }

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
        /**
         * |--------------------------| | Error | |--------------------------|
         */
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(stringToConsole(errorMessage, Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
    }
}
