package main;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.Exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.dao.FinanceRepositoryImpl;
import src.entity.Expense;
import src.exception.UserNotFoundException;
import src.exception.ExpenseNotFoundException;

import java.sql.Connection;
import src.util.DBConnUtil;

class exception_test {

    private FinanceRepositoryImpl repo;
    private Connection conn;

    @BeforeEach
    void setUp() {
        conn = DBConnUtil.getConnection("db.properties");
        repo = new FinanceRepositoryImpl(conn);
    }

    @Test
    void testDeleteUser_ThrowsUserNotFoundException() {
        int user_id = 9999;
        Exception exception = assertThrows(UserNotFoundException.class, () -> repo.deleteUser(user_id));
        assertEquals("User with ID " + user_id + " does not exist.", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    void testDeleteExpense_ThrowsExpenseNotFoundException() {
        int expense_id = 9999;
        Exception exception = assertThrows(ExpenseNotFoundException.class, () -> repo.deleteExpense(expense_id));
        assertEquals("Expense with ID " + expense_id + " does not exist.", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    void testUpdateExpense_ThrowsExpenseNotFoundException() {
    	int expense_id =9999;
        Expense expense_obj = new Expense(1, expense_id, 200.0, 3, new java.util.Date(), "Updated Description");
        Exception exception =  assertThrows(ExpenseNotFoundException.class, () -> repo.updateExpense(expense_obj));
        assertEquals("Expense with ID " + expense_id + " does not exist.", exception.getMessage());
        System.out.println(exception.getMessage());
        
    }

    
}
