package main.command;

import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InsufficientFundsException;
import main.exception.InterruptedOperationException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.withdraw_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.requestCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        Map<Integer, Integer> withdrawMap;
        int expectedAmount;
        do {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (expectedAmount <= 0) throw new IllegalArgumentException();
                if (!manipulator.isAmountAvailable(expectedAmount)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                withdrawMap = manipulator.withdrawAmount(expectedAmount);
            }
            catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            } catch (InsufficientFundsException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
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

        String out = String.format(res.getString("success.format"), expectedAmount, manipulator.getCurrencyCode());
        ConsoleHelper.writeMessage(out);

    }
