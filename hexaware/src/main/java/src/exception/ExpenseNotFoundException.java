package src.exception;

public class ExpenseNotFoundException extends Exception 
{
    public ExpenseNotFoundException(String msg) 
    {
        super(msg);
    }
}
