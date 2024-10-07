package main;
import src.main.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import src.util.*;
import src.dao.*;
import src.entity.*;



import org.junit.jupiter.api.Test;
class search_expense {
	private FinanceRepositoryImpl financeRepository;
    private Connection conn;
	
	@Test
	void testGetAllExpenses_Success() throws SQLException {
		 conn = DBConnUtil.getConnection("db.properties");
	        if (conn != null) {
	            System.out.println("Connection established.");
	        }
	    // Arrange
	    int userId = 3;  // Assuming user with ID 1 exists and has expenses
	    FinanceRepositoryImpl financeRepository = new FinanceRepositoryImpl(conn);

	    // Act
	    List<Expense> expenses = financeRepository.getAllExpenses(userId);

	    // Assert
	    assertNotNull(expenses, "Error -> The expenses list should not be null");
	    assertTrue(expenses.size() > 0, "Error -> Expected expenses for the user but got an empty list");
	}


}
