package main.command;

import main.ConsoleHelper;
import main.exception.InterruptedOperationException;

public class LoginCommand implements Command{
    //These values were required to be hardcoded
    long cardNumber = 123456789012L;
    int pin = 1234;
    @Override
    public void execute() throws InterruptedOperationException {
        boolean isValidated = false;
        while (!isValidated) {
            long cardNumberUser;
            int pinUser;
            try {
                ConsoleHelper.writeMessage("Enter 12-digit credit card number:");
                cardNumberUser = Long.parseLong(ConsoleHelper.readString());
                ConsoleHelper.writeMessage("Enter 4-digit PIN code:");
                pinUser = Integer.parseInt(ConsoleHelper.readString());
            }
            catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Invalid input.");
                continue;
            }
            if (cardNumberUser != cardNumber || pinUser != pin) {
                ConsoleHelper.writeMessage("Invalid input.");
            }
            else {
                isValidated = true;
                ConsoleHelper.writeMessage("Verification successful.");
            }
        }
    }
}

