package main;
import src.main.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import src.util.*;
import src.dao.*;
import src.entity.*;

import org.junit.jupiter.api.Test;

class expense_test {
	  private FinanceRepositoryImpl financeRepository;
	    private Connection conn;


	@Test
	void testCreateExpense_Success() throws SQLException {
		 conn = DBConnUtil.getConnection("db.properties");
	        if (conn != null) {
	            System.out.println("Connection established.");
	        }
	    // Arrange
	    Expense testExpense = new Expense(3, 0, 150.0, 2, new java.util.Date(), "Groceries");
	    FinanceRepositoryImpl financeRepository = new FinanceRepositoryImpl(conn);

	    // Act
	    boolean result = financeRepository.createExpense(testExpense);

	    // Assert
	    assertTrue(result, "Error -> Expense was not created successfully");
	}


}
