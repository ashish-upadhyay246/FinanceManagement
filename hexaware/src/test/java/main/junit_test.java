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
class junit_test {

    private FinanceRepositoryImpl financeRepository;
    private Connection conn;

    @Test
    void testCreateUser() throws SQLException {
    	System.out.println("hello");
   	 	conn = DBConnUtil.getConnection("db.properties");
        if (conn != null) {
            System.out.println("Connection established.");
        }
    	FinanceRepositoryImpl obj = new FinanceRepositoryImpl(conn);
        User testUser = new User(0, "jatin", "Jatin@123", "jatin@gmail.com");

        // Act
        boolean result = obj.createUser(testUser);

        // Assert
        assertTrue(result, "Error -> User was not created successfully");

    }
}
