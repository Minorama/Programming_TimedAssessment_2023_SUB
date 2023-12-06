import exceptions.InsufficientFundsException; // Imports the InsufficientFundsException class to be used in the withdraw method 
import java.text.DecimalFormat; // Imports the DecimalFormat class to format the currentBalance
import java.text.NumberFormat; // Imports the NumberFormat class to format the accountID

public class BankAccount {

    private int accountID; // Instance variable for the account ID
    private double currentBalance; // Instance variable for the current balance

    
    public BankAccount(int accountID, double currentBalance) { //The constructor method takes the account ID and current balance as parameters
        this.accountID = accountID; // Assigns the account ID parameter to the relevent instance variable
        this.currentBalance = currentBalance; // Assigns the current balance parameter to the relevent instance variable
    }
    

    
    public int getAccountID(){
        return accountID; // This method returns the accountID instance variable
    }
    

    
    public double getCurrentBalance(){
        return currentBalance; // This method returns the currentBalance instance variable
    }
    

    
    public void deposit(double deposit) throws IllegalArgumentException { //A method that facilitates deposits to the account
        if(deposit <= 0){ // Checks if the deposit amount is less than or equal to 0 and throws an exception if it is, if not execution continues
            throw new IllegalArgumentException("Deposit amount must be greater than 0.00!");
        }
        currentBalance += deposit; //Updates the currentBalance variable by adding the deposit amount
    }

    
    public void withdraw(double withdrawal) throws InsufficientFundsException, IllegalArgumentException { //A method that facilitates withdrawals from the account
        if(withdrawal <= 0){ // Checks if the withdrawal amount is less than or equal to 0 and throws an exception if it is, if not execution continues
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.00!");
        }
        if(withdrawal > currentBalance){ // Checks if the withdrawal amount is greater than the current balance and throws an exception if it is, if not execution continues
            throw new InsufficientFundsException(currentBalance, withdrawal);
        }
        currentBalance -= withdrawal; //Updates the currentBalance variable by subtracting the withdrawal amount
    }
    

    
    public String getFormattedAccountNumber(){ // A method to format the accountID to the standard 8 digit format with leading zeros if necessary
        NumberFormat formatter = new DecimalFormat("00000000"); // Creates a formatter object with the standard 8 digit format
        return formatter.format(accountID); // Returns the formatted account number
    }
    

    
    public String getFormattedCurrentBalance(){ // A method to format the currentBalance to a standard currency format
        NumberFormat formatter = new DecimalFormat("£###,###,##0.00"); // Creates a formatter object with the a standard currency format up to 100 million pounds
        return formatter.format(currentBalance); // returns the formatted current balance
    }
    

    
    @Override
    public String toString() { // A method to return a string representation of the entire account
        return "A/C No: " + getFormattedAccountNumber() + ", Balance: " + getFormattedCurrentBalance();
    }





    /* =======================================
        DO NOT EDIT OR REMOVE THE BELOW CODE!
    ======================================= */

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BankAccount){
            return (((BankAccount) obj).accountID == accountID) && ((BankAccount) obj).currentBalance == currentBalance;
        }else{
            return super.equals(obj);
        }
    }
}
