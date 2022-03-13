package main.command;

import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command{
    @Override
    public void execute() {
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
        if (!hasMoney) ConsoleHelper.writeMessage("No money available.");

    }
}