package interfaces;

import exception.InvalidExpenseException;

public interface ExpenseAmountValidator {
    void validateAmount(double amount) throws InvalidExpenseException;
}
