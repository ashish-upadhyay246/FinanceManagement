package src.main;
import src.dao.*;

import src.entity.*;
import src.util.*;
import src.exception.*;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class FinanceApp {
    public static void main(String[] args) {
        try (Connection c = DBConnUtil.getConnection("db.properties")) {
            FinanceRepositoryImpl fr = new FinanceRepositoryImpl(c);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nMAIN MENU:");
                System.out.println("1. Authenticate User");
                System.out.println("2. Add User");
                System.out.println("3. Delete User");
                System.out.println("4. Exit");
                System.out.println("Choose an option: ");

                int ch1 = sc.nextInt();
                sc.nextLine();
                switch (ch1) {
                    case 1:
                    	//authenticate the user
                        System.out.println("Enter username: ");
                        String un = sc.nextLine();
                        System.out.println("Enter password: ");
                        String pwd = sc.nextLine();
                        int rID=fr.authenticateUser(un,pwd);
                        
                        if (rID>0) {
                        	while(true) 
                        	{
                        	System.out.println("\nUserID of current user: "+rID);
                            System.out.println("\nEnter a choice:");
                            System.out.println("1. Add Expense");
                            System.out.println("2. Delete Expense");
                            System.out.println("3. Update Expense");
                            System.out.println("4. Get All Expenses");
                            System.out.println("5. Exit");
                            int ch = sc.nextInt();
                            sc.nextLine();
                            switch(ch) {                                
                            case 1:
                            	//add an expense
                            	System.out.println("Enter userId, amount, categoryId, date (yyyy-mm-dd), description:");
                                Expense ex = new Expense(sc.nextInt(),0, sc.nextDouble(), sc.nextInt(), java.sql.Date.valueOf(sc.next()), sc.next());
                                fr.createExpense(ex);
                                System.out.print("Expense Created!");
                                break;
                                
                            case 2:
                                // delete an expense
                                System.out.println("Enter expenseId to delete:");
                                int eID = sc.nextInt();
                                sc.nextLine();
                                try 
                                {
                                	fr.deleteExpense(eID);
                                    System.out.print("Expense deleted.");
                                } 
                                catch (ExpenseNotFoundException e) 
                                {
                                    System.out.print(e.getMessage());
                                }
                                break;

                            case 3:
                                // update an expense
                                System.out.println("Enter userId, expenseId, amount, categoryId, date (yyyy-mm-dd), description:");
                                int u = sc.nextInt();
                                sc.nextLine();
                                int exID = sc.nextInt();
                                sc.nextLine();
                                double am = sc.nextDouble();
                                sc.nextLine();
                                int caID = sc.nextInt();
                                sc.nextLine();
                                java.sql.Date dt = java.sql.Date.valueOf(sc.next());
                                sc.nextLine();
                                String dsc = sc.nextLine();
                                
                                Expense upex = new Expense(u, exID, am, caID, dt, dsc);
                                
                                try {
                                    fr.updateExpense(upex);
                                    System.out.print("Expense updated!");
                                } catch (ExpenseNotFoundException e) {
                                    System.out.print(e.getMessage());
                                }
                                break;

                            
                            case 4:
                            	//show all expenses for a certain userID
                            	System.out.println("Enter your userId to view all expenses:");
                            	int uID = sc.nextInt();                            	
                                List<Expense> exps = fr.getAllExpenses(uID);
                                if (exps.isEmpty()) 
                                {
                                    System.out.print("No expenses found.");
                                } 
                                else 
                                {
                                    System.out.println("Expenses:");
                                    for (Expense exp : exps) {
                                        System.out.println("Expense ID: " + exp.getExpenseId() + ", Amount: " + exp.getAmount() + ", Category ID: " + exp.getCategoryId() + ", Date: " + exp.getDate() + ", Description: " + exp.getDescription());
                                    }
                                }                            	                           	                                
                                break;
                                
                            case 5:
                            	//log out for current user
                            	System.out.println("Logging out. Exiting the app...");
                                return;
                                
                            default:
                                System.out.print("Invalid choice!");
                            }
                        }
                       } 
                        else 
                        {
                            System.out.println("Invalid username or password!");
                        }
                        break;
                        
                    case 2:
                    	//create a new user
                    	System.out.println("Enter username, password, email:");
                        User user = new User(0, sc.next(), sc.next(), sc.next());
                        fr.createUser(user);
                        System.out.print("User created.");
                        break;
                        
                    case 3:
                    	//delete an existing user
                    	System.out.println("Authenticate yourself");
                    	System.out.print("Enter username: ");
                        String un2 = sc.nextLine();
                        System.out.print("Enter password: ");
                        String pwd2 = sc.nextLine();
                        int dID=fr.authenticateUser(un2, pwd2);
                        if (dID>0) 
                        {
                        	System.out.println("Enter the userId to delete:");
                            int utoD = sc.nextInt();
                            try 
                            {
                                boolean iD = fr.deleteUser(utoD);
                                if (iD) 
                                {
                                    System.out.print("User deleted successfully.");
                                }
                            } 
                            catch (UserNotFoundException e) 
                            {
                                System.out.print(e.getMessage());
                            }
                        }
                        else 
                        {
                            System.out.print("Invalid username or password.");
                        }                    	
                        break;
                        
                    case 4:
                    	//exit the application
                    	System.out.println("Exiting the app...");
                        return;   
                        
                    default:
                        System.out.print("Invalid choice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
