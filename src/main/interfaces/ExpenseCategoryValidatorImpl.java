package main.interfaces;

import main.exception.InvalidCategoryException;

public class ExpenseCategoryValidatorImpl implements ExpenseCategoryValidator {
    @Override
    public String validateCategory(String categoryName) throws InvalidCategoryException{
        if(!categoryName.equals("ordinario") && !categoryName.equals("extraordinario")){
            throw new InvalidCategoryException("Categoria Invalida. Debe ingresar 'Ordinario' o 'Extraordinario'");
        }
        return categoryName;
    }
}
