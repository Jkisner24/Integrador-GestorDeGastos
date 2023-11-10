package interfaces;

import exception.InvalidCategoryException;

@FunctionalInterface
public interface ExpenseCategoryValidator {
    String validateCategory(String categoryName) throws InvalidCategoryException;
}
