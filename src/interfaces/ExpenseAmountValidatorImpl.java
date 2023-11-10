package interfaces;

import exception.InvalidExpenseException;

public class ExpenseAmountValidatorImpl implements ExpenseAmountValidator {
    @Override
    public void validateAmount(double amount) throws InvalidExpenseException {
        if (amount < 0) {
            throw new InvalidExpenseException("El monto debe ser igual o mayor a cero");
        }
    }
}
