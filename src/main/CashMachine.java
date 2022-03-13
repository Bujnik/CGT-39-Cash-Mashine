package main;

import main.command.CommandExecutor;
import main.exception.InterruptedOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        //Following line secures validator problems
        Locale.setDefault(Locale.ENGLISH);

        Operation operation = null;
        do {
            try {
                operation = ConsoleHelper.requestOperation();
                CommandExecutor.execute(operation);
            } catch (InterruptedOperationException e) {
                ConsoleHelper.writeMessage("Goodbye");
                break;
            }
        }while (!operation.equals(Operation.EXIT));

    }
}
