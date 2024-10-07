package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.dao.FinanceRepositoryImpl;
import src.entity.Expense;
import src.exception.UserNotFoundException;
import src.exception.ExpenseNotFoundException;

import java.sql.Connection;
import src.util.DBConnUtil;

class exception_test {

    private FinanceRepositoryImpl financeRepository;
    private Connection conn;

    @BeforeEach
    void setUp() {
        // Set up database connection and repository
        conn = DBConnUtil.getConnection("db.properties");
        financeRepository = new FinanceRepositoryImpl(conn);
    }

    @Test
    void testDeleteUser_ThrowsUserNotFoundException() {
        int nonExistentUserId = 9999; // Assume this user ID doesn't exist
        assertThrows(UserNotFoundException.class, () -> financeRepository.deleteUser(nonExistentUserId));
    }

    @Test
    void testDeleteExpense_ThrowsExpenseNotFoundException() {
        int nonExistentExpenseId = 9999; // Assume this expense ID doesn't exist
        assertThrows(ExpenseNotFoundException.class, () -> financeRepository.deleteExpense(nonExistentExpenseId));
    }

    @Test
    void testUpdateExpense_ThrowsExpenseNotFoundException() {
        Expense nonExistentExpense = new Expense(1, 9999, 200.0, 3, new java.util.Date(), "Updated Description");
        assertThrows(ExpenseNotFoundException.class, () -> financeRepository.updateExpense(nonExistentExpense));
    }

    
}
