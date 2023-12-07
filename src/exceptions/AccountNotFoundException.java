package exceptions;

public class AccountNotFoundException extends RuntimeException {

    
    public AccountNotFoundException(int invalidAccountID) { //Takes the invalid account ID as a parameter
        super("Account not found with accountID: " + invalidAccountID); //Constructs an exception with the message and specific invalid account ID
    }

}
