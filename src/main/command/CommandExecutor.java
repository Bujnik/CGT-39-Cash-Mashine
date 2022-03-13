package main.command;

import main.Operation;
import main.exception.InterruptedOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    private CommandExecutor() {
    }

    static {
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptedOperationException {
        allKnownCommandsMap.get(operation).execute();
    }

}
