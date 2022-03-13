package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InterruptedOperationException;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> set = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean hasMoney = false;
        for (CurrencyManipulator c : set) {
            if (c.hasMoney()) {
                hasMoney = true;
            }
            else continue;
            ConsoleHelper.writeMessage(c.getCurrencyCode()
                    + " - "
                    + c.getTotalAmount());
        }
        if (!hasMoney) ConsoleHelper.writeMessage(res.getString("no.money"));

    }
}
