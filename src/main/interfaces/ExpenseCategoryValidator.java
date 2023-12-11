package main.interfaces;

import main.exception.InvalidCategoryException;

@FunctionalInterface
public interface ExpenseCategoryValidator {
    String validateCategory(String categoryName) throws InvalidCategoryException;
}
