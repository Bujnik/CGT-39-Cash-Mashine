package main.command;

import main.ConsoleHelper;
import main.exception.InterruptedOperationException;

class ExitCommand implements Command{
    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage("Do you really want to exit?");
        ConsoleHelper.writeMessage("Y/N");
        String s = ConsoleHelper.readString();
        if (s.equalsIgnoreCase("Y")) ConsoleHelper.writeMessage("Goodbye!");

    }
}
