package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InterruptedOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.deposit_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.requestCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        String[] pairArr = ConsoleHelper.getTwoValidNumbers(currencyCode);
        int denomination = 0;
        int numberOfBanknotes = 0;
        try {
            denomination = Integer.parseInt(pairArr[0]);
            numberOfBanknotes = Integer.parseInt(pairArr[1]);
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }

        manipulator.addAmount(denomination, numberOfBanknotes);
        String out = String.format(res.getString("success.format"), denomination, manipulator.getCurrencyCode());
        ConsoleHelper.writeMessage(out);
    }
}
