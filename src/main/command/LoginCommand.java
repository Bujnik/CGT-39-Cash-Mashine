package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.exception.InterruptedOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

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
            if (!validCreditCards.containsKey(String.valueOf(cardNumberUser))
                    || !validCreditCards.getString(String.valueOf(cardNumberUser)).equalsIgnoreCase(String.valueOf(pinUser)) ) {
                ConsoleHelper.writeMessage("Invalid input.");
            }
            else {
                isValidated = true;
                ConsoleHelper.writeMessage("Verification successful.");
            }
        }
    }
}

