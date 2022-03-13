package main;

public enum Operation {
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        //User requests operation by number in range 1-4.
        try {
            return Operation.values()[i - 1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }
}
