import java.text.NumberFormat; // Import the NumberFormat class to format the interest rate in the getFormattedInterestRate() method; and support the getEstimatedAnnualReturn() and getEstimatedMonthlyReturn() methods
import java.text.DecimalFormat; // Import the DecimalFormat class to format the estimated returns in the getEstimatedAnnualReturn() and getEstimatedMonthlyReturn() methods

public class SavingsAccount extends BankAccount {

    private double interestRate; // Instance variable for the interest rate

    
    public SavingsAccount(int accountID, double currentBalance, double interestRate) {
        super(accountID, currentBalance); // Calls the constructor of the superclass and assigns the account ID and current balance parameters to the relevent instance variables
        this.interestRate = interestRate; // Assigns the interest rate parameter to the relevent instance variable
    }
    
    
    public double getInterestRate(){
        return interestRate; // This method returns the interestRate instance variable
    }
    

    
    public String getFormattedInterestRate() { // This method formats the interest rate to a percentage
        NumberFormat formatter = NumberFormat.getPercentInstance(); // Creates a formatter object with the standard percentage format
        formatter.setMinimumFractionDigits(1); // Insures it will always display to at least 1 decimal place
        return formatter.format(interestRate); // Returns the formatted interest rate
    }
    

    
    public void setInterestRate(double newInterestRate) throws IllegalArgumentException { // This method sets the interest rate to a new valid value
        if (newInterestRate < 0) {
            throw new IllegalArgumentException("Interest Rate cannot be less than 0%!"); // Throws an exception if the interest rate is less than 0, if not execution continues
        }
        if (newInterestRate >= 1) {
            throw new IllegalArgumentException("Interest Rate cannot be greater than 100%!"); // Throws an exception if the interest rate is greater than or equal to 1, if not execution continues
        }
        interestRate = newInterestRate; // Assigns the new interest rate to the relevent instance variable, after insureing its a valid value
    }
    

    
    public String getEstimatedAnnualReturn(){ // This method calculates the estimated annual return
        double annualReturn = getCurrentBalance() * interestRate; // Calculates the annual return
        NumberFormat formatter = new DecimalFormat("£###,###,##0.00"); // Formats the annual return to a standard currency format up to 100 million pounds
        return formatter.format(annualReturn); // Returns the formatted annual return
    }
    

    
    public String getEstimatedMonthlyReturn(){ // This method calculates the estimated monthly return
        double monthlyReturn = (getCurrentBalance() * interestRate) / 12; // Calculates the monthly return
        NumberFormat formatter = new DecimalFormat("£###,###,##0.00"); // Formats the monthly return to a standard currency format up to 100 million pounds
        return formatter.format(monthlyReturn); // Returns the formatted monthly return
    }
    

    
    @Override
    public String toString() { // This method returns a string representation of the entire Savings Account
        return "A/C No: " + getFormattedAccountNumber() + ", Balance: " + getFormattedCurrentBalance() + ", Interest Rate: "+ getFormattedInterestRate();
    }
    

}
