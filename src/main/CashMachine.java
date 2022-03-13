package main;

import main.command.CommandExecutor;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        //Following line secures validator problems
        Locale.setDefault(Locale.ENGLISH);

        Operation operation = null;
        do {
            operation = ConsoleHelper.requestOperation();
            CommandExecutor.execute(operation);
        }while (!operation.equals(Operation.EXIT));

    }
}
