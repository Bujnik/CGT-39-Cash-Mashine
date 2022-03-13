package main;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        //User requests operation by number in range 1-4.
        //User can not choose LOGIN operation
        if (i == 0) throw new IllegalArgumentException();
        try {
            return Operation.values()[i];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }
}
