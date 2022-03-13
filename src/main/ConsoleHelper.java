package main;

import main.exception.InterruptedOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static final BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptedOperationException {
        String s = null;
        try{
            s = bis.readLine();
            if (s.equalsIgnoreCase("EXIT")) {
                printExitMessage();
                throw new InterruptedOperationException();
            }
        } catch (IOException ignored) {
        }
        return s;
    }

    public static String requestCurrencyCode() throws InterruptedOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String s = null;
        while (s == null) {
            s = readString();
            //Code has to be 3 characters long
            if (s.length() != 3) {
                s = null;
                writeMessage(res.getString("invalid.data"));
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
            String out = String.format(res.getString("choose.denomination.and.count.format"), currencyCode);
            writeMessage(out);
            String[] arr = readString().split(" ");
            //arr needs to have only 2 elements
            if (arr.length != 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            try {
                denomination = Integer.parseInt(arr[0]);
                numberOfBanknotes = Integer.parseInt(arr[1]);
                //Integers have to be positive values
                if (denomination < 0 || numberOfBanknotes < 0) throw new InterruptedOperationException();
            }
            catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
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
                writeMessage(res.getString("choose.operation"));
                writeMessage("1 - " + res.getString("operation.INFO"));
                writeMessage("2 - " + res.getString("operation.DEPOSIT"));
                writeMessage("3 - " + res.getString("operation.WITHDRAW"));
                writeMessage("4 - " + res.getString("operation.EXIT"));

                number = Integer.parseInt(readString());
                operation = Operation.getAllowableOperationByOrdinal(number);
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }while (true);
        return operation;
    }


    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
