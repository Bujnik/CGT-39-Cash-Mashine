package main.command;

import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InterruptedOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptedOperationException {
        String currencyCode = ConsoleHelper.requestCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        String[] pairArr = ConsoleHelper.getTwoValidNumbers(currencyCode);
        int denomination = Integer.parseInt(pairArr[0]);
        int numberOfBanknotes = Integer.parseInt(pairArr[1]);

        manipulator.addAmount(denomination, numberOfBanknotes);
    }
}
