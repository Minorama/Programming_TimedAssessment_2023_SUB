package exceptions;

public class InsufficientFundsException extends RuntimeException {

    
    public InsufficientFundsException(double currentBalance, double amountToWithdraw) { //Takes the current balance and amount to withdraw as parameters
        super(String.format("Insufficient funds for this withdrawal. (Available Balance:%s, Amount Requested:%s)", currentBalance, amountToWithdraw)); //Constructs an exception with the message and specific current balance and amount to withdraw explaining the problem
    }
    
}
