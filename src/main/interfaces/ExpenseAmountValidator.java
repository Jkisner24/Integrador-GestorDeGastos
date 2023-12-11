package main.interfaces;

import main.exception.InvalidExpenseException;

public interface ExpenseAmountValidator {
    boolean validateAmount(double amount) throws InvalidExpenseException;
}
