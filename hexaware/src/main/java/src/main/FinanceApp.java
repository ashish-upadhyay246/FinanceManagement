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
    	//loading driver
    	try 
    	{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded..");
		}
    	catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Driver loading failed...");
		}
    	
    	//creating connection
        try (Connection c = DBConnUtil.getConnection("db.properties")) 
        {
        	Scanner sc = new Scanner(System.in);
        	FinanceRepositoryImpl obj = new FinanceRepositoryImpl(c);
           
            while (true) {
                System.out.println("\nMain menu: Finance Management");
                System.out.println("1. Authentication");
                System.out.println("2. Delete User");
                System.out.println("3. Add User");                
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
                        int rID=0;
                        try 
                        {
                        	rID=obj.authenticateUser(un,pwd);
                        } 
                        catch (UserNotFoundException e) 
                        {
                            System.out.print("User does not exist!");
                        }
                        
                        if (rID>0) {
                        	while(true) 
                        	{
                        	System.out.println("\nActive UserID: "+rID);
                            System.out.println("\nEnter a choice:");
                            System.out.println("1. Get every expense");
                            System.out.println("2. Add an expense");
                            System.out.println("3. Update an expense"); 
                            System.out.println("4. Delete an expense");                                                       
                            System.out.println("5. Exit the user");
                            int ch = sc.nextInt();
                            sc.nextLine();
                            switch(ch) {     
                            case 1:
                            	//show all expenses for a certain userID
                            	System.out.println("Enter your userId to view all expenses:");
                            	int uID = sc.nextInt();
                            	sc.nextLine();
                                List<Expense> exps = obj.getAllExpenses(uID);
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
                                
                            case 2:
                            	//add an expense
                            	System.out.println("Enter userId, amount, categoryId, date (yyyy-mm-dd), description:");
                            	int usID=sc.nextInt();
                            	sc.nextLine();
                            	double amnt=sc.nextDouble();
                            	sc.nextLine();
                            	int cID=sc.nextInt();
                            	sc.nextLine();
                            	java.sql.Date dte=java.sql.Date.valueOf(sc.next());
                            	sc.nextLine();
                            	String d=sc.nextLine();
                            	
                            	Expense ex = new Expense(usID,0, amnt, cID, dte, d);
                            	obj.createExpense(ex);
                                System.out.print("Expense Created!");
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
                                	obj.updateExpense(upex);
                                    System.out.print("Expense updated!");
                                } catch (ExpenseNotFoundException e) {
                                    System.out.print(e.getMessage());
                                }
                                break;
                                
                            case 4:
                                // delete an expense
                                System.out.println("Enter expenseId to delete:");
                                int eID = sc.nextInt();
                                sc.nextLine();
                                try 
                                {
                                	obj.deleteExpense(eID);
                                    System.out.print("Expense deleted.");
                                } 
                                catch (ExpenseNotFoundException e) 
                                {
                                    System.out.print(e.getMessage());
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
                        break;
                        
                    case 2:
                    	//delete an existing user
                    	System.out.println("Authenticate yourself");
                    	System.out.print("Enter username: ");
                        String un2 = sc.nextLine();
                        System.out.print("Enter password: ");
                        String pwd2 = sc.nextLine();
                        int dID=0;
                        try 
                        {
                        	dID=obj.authenticateUser(un2,pwd2);
                        } 
                        catch (UserNotFoundException e) 
                        {
                            System.out.print("User does not exist");
                        }
                        if (dID>0) 
                        {
                        	System.out.println("Enter the userId to delete:");
                            int utoD = sc.nextInt();
                            try 
                            {
                                boolean iD = obj.deleteUser(utoD);
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
                        break;
                        
                    case 3:
                    	//create a new user
                    	System.out.println("Enter username, password, email:");
                        User user = new User(0, sc.next(), sc.next(), sc.next());
                        obj.createUser(user);
                        System.out.print("User created.");
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
