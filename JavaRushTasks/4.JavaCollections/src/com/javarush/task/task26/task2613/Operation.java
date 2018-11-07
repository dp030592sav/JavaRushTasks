package com.javarush.task.task26.task2613;

public enum Operation {
    INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        try {
            return Operation.values()[i - 1];
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
