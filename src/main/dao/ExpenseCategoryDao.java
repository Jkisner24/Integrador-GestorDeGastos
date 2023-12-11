package main.dao;

import main.dao.dto.ExpenseCategoryDto;
import main.entities.ExpenseCategory;
import main.exception.DAOException;

public interface ExpenseCategoryDao {
    void insert(ExpenseCategoryDto expense) throws DAOException;
    ExpenseCategory getCategoryByName(String name) throws DAOException;
}
