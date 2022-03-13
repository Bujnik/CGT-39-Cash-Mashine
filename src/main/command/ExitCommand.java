package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.exception.InterruptedOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String s = ConsoleHelper.readString();
        if (s.equalsIgnoreCase("Y")) ConsoleHelper.writeMessage(res.getString("thank.message"));

    }
}
