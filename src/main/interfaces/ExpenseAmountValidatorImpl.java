package main.interfaces;

import main.exception.InvalidExpenseException;

public class ExpenseAmountValidatorImpl implements ExpenseAmountValidator {
    @Override
    public boolean validateAmount(double amount) throws InvalidExpenseException {
        if (amount < 0) {
            throw new InvalidExpenseException("El monto debe ser igual o mayor a cero");
        }
        return true;
    }
}
