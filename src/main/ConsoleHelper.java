package main;

import main.exception.InterruptedOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class ConsoleHelper {
    private static final BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptedOperationException {
        String s = null;
        try{
            s = bis.readLine();
            if (s.equalsIgnoreCase("EXIT")) throw new InterruptedOperationException();
        } catch (IOException ignored) {
        }
        return s;
    }

    public static String requestCurrencyCode() throws InterruptedOperationException {
        writeMessage("Enter currency code: ");
        String s = null;
        while (s == null) {
            s = readString();
            //Code has to be 3 characters long
            if (s.length() != 3) {
                s = null;
                writeMessage("Invalid code.");
            }
        }
        return s.toUpperCase(Locale.ROOT);
    }

    /**
     * For some reason requirement is to return String array, instead of Integer
     */
    public static String[] getTwoValidNumbers(String currencyCode) throws InterruptedOperationException {
        int denomination;
        int numberOfBanknotes;
        do{
            writeMessage("Enter denomination and number of banknotes, separated by space");
            String[] arr = readString().split(" ");
            //arr needs to have only 2 elements
            if (arr.length != 2) {
                writeMessage("Invalid input.");
                continue;
            }
            try {
                denomination = Integer.parseInt(arr[0]);
                numberOfBanknotes = Integer.parseInt(arr[1]);
                //Integers have to be positive values
                if (denomination < 0 || numberOfBanknotes < 0) throw new InterruptedOperationException();
            }
            catch (Exception e) {
                writeMessage("Invalid input.");
                continue;
            }
            break;
        } while (true);
        return new String[]{String.valueOf(denomination), String.valueOf(numberOfBanknotes)};
    }

    public static Operation requestOperation() throws InterruptedOperationException{
        int number;
        Operation operation;
        do{
            try{
                writeMessage("Enter operation ID: ");
                writeMessage("1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
                number = Integer.parseInt(readString());
                operation = Operation.getAllowableOperationByOrdinal(number);
            } catch (IllegalArgumentException e) {
                writeMessage("Invalid input.");
                continue;
            }
            break;
        }while (true);
        return operation;
    }


}
