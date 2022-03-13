package main.command;

import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InsufficientFundsException;
import main.exception.InterruptedOperationException;

import java.util.Map;

class WithdrawCommand implements Command{
    @Override
    public void execute() throws InterruptedOperationException {
        String currencyCode = ConsoleHelper.requestCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        Map<Integer, Integer> withdrawMap;
        int expectedAmount;
        do {
            ConsoleHelper.writeMessage("Enter amount to withdraw:");
            try {
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (expectedAmount <= 0) throw new IllegalArgumentException();
                if (!manipulator.isAmountAvailable(expectedAmount)) throw new IllegalArgumentException();
                withdrawMap = manipulator.withdrawAmount(expectedAmount);
            }
            catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage("Invalid input.");
                continue;
            } catch (InsufficientFundsException e) {
                ConsoleHelper.writeMessage("Insufficient banknotes in the machine.");
                continue;
            }
            break;
        }while (true);


        //Print results
        for (Map.Entry<Integer, Integer> pair : withdrawMap.entrySet()) {
            ConsoleHelper.writeMessage("\t"
                    + pair.getKey()
                    + " - "
                    + pair.getValue());
        }

    }
}
