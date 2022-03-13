package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.exception.InterruptedOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");


    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean isValidated = false;
        while (!isValidated) {
            long cardNumberUser;
            int pinUser;
            try {
                ConsoleHelper.writeMessage(res.getString("specify.data"));
                cardNumberUser = Long.parseLong(ConsoleHelper.readString());
                pinUser = Integer.parseInt(ConsoleHelper.readString());
                if (String.valueOf(cardNumberUser).length() != 12
                        || String.valueOf(pinUser).length() != 4) throw new NumberFormatException();
            }
            catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            if (!validCreditCards.containsKey(String.valueOf(cardNumberUser))
                    || !validCreditCards.getString(String.valueOf(cardNumberUser)).equalsIgnoreCase(String.valueOf(pinUser)) ) {
                String out = String.format(res.getString("not.verified.format"), cardNumberUser);
                ConsoleHelper.writeMessage(out);
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
            else {
                isValidated = true;
                String out = String.format(res.getString("success.format"), cardNumberUser);
                ConsoleHelper.writeMessage(out);
            }
        }
    }
}

