package main;

import main.command.CommandExecutor;
import main.exception.InterruptedOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";
    public static void main(String[] args) {
        //Following line secures validator problems
        Locale.setDefault(Locale.ENGLISH);

        Operation operation;
        boolean loggedIn = false;
        do {
            try {
                //Login must be conducted before any other operation
                if (!loggedIn) {
                    CommandExecutor.execute(Operation.LOGIN);
                    loggedIn = true;
                }
                operation = ConsoleHelper.requestOperation();
                CommandExecutor.execute(operation);

            } catch (InterruptedOperationException e) {
                ConsoleHelper.printExitMessage();
                break;
            }
        }while (!operation.equals(Operation.EXIT));

    }
}
