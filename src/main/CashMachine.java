package main;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        //Following line secures validator problems
        Locale.setDefault(Locale.ENGLISH);

        String currencyCode = ConsoleHelper.requestCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        String[] entry = ConsoleHelper.getTwoValidNumbers(currencyCode);
        int denomination = Integer.parseInt(entry[0]);
        int numberOfBanknotes = Integer.parseInt(entry[1]);

        manipulator.addAmount(denomination, numberOfBanknotes);

    }
}
