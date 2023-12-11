package main.dao;

import main.dao.dto.ExpenseDto;
import main.exception.DAOException;

import java.util.List;

public interface ExpenseDao {
    ExpenseDto getById(int id) throws DAOException;
    List<ExpenseDto> getAll() throws DAOException;
    void insert(ExpenseDto expenseDto) throws DAOException;
    void update(ExpenseDto expense) throws DAOException;
    void delete(int id) throws DAOException;
}
