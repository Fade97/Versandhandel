package de.volkswagen.f73.utility;

/**
 * Produkte
 * _______________________________
 * 1) Banane
 * 2) Erdbeere
 * .
 * .
 * .
 * Warenkorb:   4 Artikel | 12.30€
 * Seite 1/10     x: nächste Seite
 */

/**
 * 
 * @author Fabian Dürkop 
 */
public class ConsoleHandler {

    private static int WIDTH = 55;
    private static int HEIGHT = 9;
    
    private static boolean BORDER = true;
    private static boolean NO_BORDER = false;

    /**
     * Possible alignment options
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
        // Produkt auswählen
        printWelcome();
        printLogin();

    }

    private void printWelcome() {
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(stringToConsole("Welcome", Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', "Welcome".length(), Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));

    }

    private boolean printLogin() {
        
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(stringToConsole("Login", Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine(' ', WIDTH-2, Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
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
    }

    private void printEditAccount() {

    }

    private void printProducts() {

    }

    private void printReceipt() {

    }

    /**
     * Prints a whole line in the console using the char c
     * @param c - the char to fill the line with
     * @param length - the length to fill; must be smaller or equal to the max size WIDTH
     * @param align - Alignment parameter
     * @param hasBorders - print the '|' char on both sides
     * @return - a String containing the line
     * @see WIDTH
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
     * Convert your desired text to a formatted console line
     * @param text - your desired text
     * @param align - Alignment parameter
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

    private void printError(String errorMessage) {
        /**
         * |--------------------------| | Error | |--------------------------|
         */
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
        System.out.println(stringToConsole(errorMessage, Alignment.CENTER, BORDER));
        System.out.println(wholeLine('-', WIDTH, Alignment.CENTER, NO_BORDER));
    }
}
