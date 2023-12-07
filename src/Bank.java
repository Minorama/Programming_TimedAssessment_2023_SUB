import exceptions.AccountNotFoundException; // Imports the AccountNotFoundException class to be used in the getAccount(), getAccountIndex(), and transferFunds() methods; to handle the exception if an account is not found
import exceptions.InsufficientFundsException; // Imports the InsufficientFundsException class to be used in the transferFunds() method; to handle the exception if an account does not have enough funds
import java.io.IOException; // Imports the IOException class to be used in the saveAccounts() method; to handle the exception if the file cannot be written to
import java.io.FileNotFoundException; // Imports the FileNotFoundException class to be used in the readAccounts() method; to handle the exception if the file cannot be found
import java.io.File; // Imports the File class to be used in the readAccounts() and saveAccounts methods; to create a file object for Scanner and Writer objects
import java.util.Scanner; // Imports the Scanner class to be used in the readAccounts() method; to read the file
import java.util.ArrayList; // Imports the ArrayList class to be used to store the accounts throughout the class; to store the accounts
import java.io.FileWriter; // Imports the FileWriter class to be used in the saveAccounts() method; to write to the file


public class Bank {

    private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>(); // Instance variable ArrayList of BankAccount objects to store accounts

    
    public void readAccounts(String fileName) throws FileNotFoundException { // A method to read accounts from a correctly formatted file
        File file = new File(fileName); // Instantiates a new file object with the file name passed as a parameter
        Scanner scanner = new Scanner(file); // Instansiates a scanner object to read the file
        while (scanner.hasNextLine()) { // A while loop to read each line of the file, the scanner continuing as long as it has another line to read
            String line = scanner.nextLine(); // Reads the line and assigns it to a variable
            String[] parts = line.split(","); // Splits the line into an array of strings at each comma
            int accountID = Integer.parseInt(parts[0]); // Parses the first (Account ID) element of the array to an integer and assigns it to a variable
            double currentBalance = Double.parseDouble(parts[1]); // Parses the second element (Balance) of the array to a double and assigns it to a variable
            if (parts.length == 3) { // Checks if the data is for a savingsAccount by checking the amount of elements in the array, i.e if it included in an interest rate
                double interestRate = Double.parseDouble(parts[2]); // Parses the third element (interest rate) of the array to a double and assigns it to a variable
                accounts.add(new SavingsAccount(accountID, currentBalance, interestRate)); // Creates a new SavingsAccount object with the data from the file and adds it to the accounts ArrayList  
            }
            else{ // If the data is not for a savings account
                accounts.add(new BankAccount(accountID, currentBalance)); // Creates a new BankAccount object with the data from the file and adds it to the accounts ArrayList
            }
        }
        scanner.close(); // Closes the scanner
    }

    
    public ArrayList<BankAccount> getAccounts() { // A method to return the accounts ArrayList
        return accounts;
    }
    

    
    public BankAccount getAccount(int accountToLocate) throws AccountNotFoundException { // A method to return the relevent account object to the account ID passed as a parameter from the accounts ArrayList
        boolean accountFound = false; // A boolean variable to check if the account has been found
        BankAccount accountEntry = null; // A BankAccount object to store the account entry

        for (BankAccount account : accounts) { // Iterating through the accounts ArrayList
            if (account.getAccountID() == accountToLocate) { 
                 accountEntry = account; // The account object is assigned to the accountEntry variable 
                accountFound = true; // If the account ID of the current account object matches the account ID passed as a parameter, the account is found and marked as such
            }
        }
        if (!accountFound) { // If the account is not found, an exception is thrown
            throw new AccountNotFoundException(accountToLocate);
        }
        return accountEntry; // Returns the account object
    }
    

    
    public int getAccountIndex(int accountToLocate) throws AccountNotFoundException {
        boolean accountFound = false; // A boolean variable to check if the account has been found
        int accountEntryIndex = 0; // An integer variable to store the index of the account entry

        for (BankAccount account : accounts) { // Iterating through the accounts ArrayList
            if (account.getAccountID() == accountToLocate) { 
                accountEntryIndex = accounts.indexOf(account); // The index of the acount object in the accounts ArrayList is assigned to the accountEntryIndex variable
                accountFound = true; // If the account ID of the current account object matches the account ID passed as a parameter, it is marked as such
            }
        }
        if (!accountFound) { // If the account is not found, an exception is thrown
            throw new AccountNotFoundException(accountToLocate);
        }
        return accountEntryIndex; // Returns the account's index in the accounts ArrayList
    }
    

    
    public int getNextAccountID(){ // A method to return the next available account ID
        BankAccount lastAccount = accounts.get(accounts.size() - 1); // Gets the last account in the accounts ArrayList
        return lastAccount.getAccountID() + 1; // Returns the last account's ID incremented by 1
    }
    

    
    public BankAccount openBankAccount() { // A method to open a new bank account
        BankAccount newAccount = new BankAccount(getNextAccountID(), 0); // Instantiates a new BankAccount object with the next available account ID and a balance of 0
        accounts.add(newAccount); // Adds the new account to the accounts ArrayList
        return newAccount; // Returns the new account
    }
    

    
    public SavingsAccount openSavingsAccount(double startingInterestRate) { // A method to open a new savings account
        SavingsAccount newSavingsAccount = new SavingsAccount(getNextAccountID(), 0, startingInterestRate); // Instantiates a new SavingsAccount object with the next available account ID, a balance of 0 and the interest rate passed as a parameter
        accounts.add(newSavingsAccount); // Adds the new account to the accounts ArrayList
        return newSavingsAccount; // Returns the new account
    }
    

    
    public void closeAccount(int accountToLocate) throws AccountNotFoundException { // A method to close an account
        try {
            BankAccount account = getAccount(accountToLocate); // Attempts to get the account object from the accounts ArrayList using the accountID to find it
            accounts.remove(account); // If the account is found, it is removed from the accounts ArrayList
        } catch (AccountNotFoundException e) { // If the account is not found, an exception is thrown
            throw new AccountNotFoundException(accountToLocate);
        }
    }
    

    
    public void transferFunds(int originID, int destinationID, double ammountToTransfer) throws AccountNotFoundException, InsufficientFundsException { // A method to transfer funds between accounts
        if (originID == destinationID) { // Insures that the origin and destination accounts are different, throws an exception if they are not
            throw new IllegalArgumentException("Origin and destination accounts must be different");
        }
        if (ammountToTransfer <= 0){ // Insures that the transfer amount is greater than 0, throws an exception if it is not
            throw new IllegalArgumentException("Transfer amount should be more than zero!");
        }
        boolean originAccountFound = false; // A boolean variable to check if the origin account has been found
        boolean destinationAccountFound = false; // A boolean variable to check if the destination account has been found
        for (BankAccount account : accounts) { // Iterating through the accounts ArrayList
            if (account.getAccountID() == originID) { // If the accountID of the origin account is found it updates the boolean variable to say so
                originAccountFound = true;
            }
            if (account.getAccountID() == destinationID){ // If the accountID of the destination account is found it updates the boolean variable to say so
                destinationAccountFound = true;
            }
        }
        if (!originAccountFound) { // If the origin account is not found, an exception is thrown
            throw new AccountNotFoundException(originID);
        }
        if (!destinationAccountFound) { // If the destination account is not found, an exception is thrown
            throw new AccountNotFoundException(destinationID);
        }
      
        BankAccount originAccount = getAccount(originID); // Gets the origin account object from the accounts ArrayList
        BankAccount destinationAccount = getAccount(destinationID); // Gets the destination account object from the accounts ArrayList

        try {
            originAccount.withdraw(ammountToTransfer); // Attempts to withdraw the transfer amount from the origin account
        } catch (InsufficientFundsException e) { // If the origin account does not have enough funds, an exception is thrown
            throw new InsufficientFundsException(originAccount.getCurrentBalance(), ammountToTransfer);
        }
        destinationAccount.deposit(ammountToTransfer); // Deposits the transfer amount to the destination account, if the origin account had enough funds
    }
    
    

    
    public void saveAccounts(String fileName) throws IOException { // A method to save the accounts in the accounts ArrayList to a file
        FileWriter write = new FileWriter(fileName); // Instantiates a new FileWriter object with the file name passed as a parameter
        try{ // Attempts to write the accounts to the file
            for(int i = 0; i < accounts.size(); i++){ // Iterates through the accounts ArrayList
                BankAccount accountEntry = accounts.get(i); // Assigns the current index of the accounts ArrayList to a BankAccount object
                if (accountEntry instanceof SavingsAccount){ // Checks if the account is a savings account
                    SavingsAccount savingsAccountEntry = (SavingsAccount) accountEntry; // If it is, it is cast to a SavingsAccount object to be able to access the getInterestRate() method
                    write.write(+ savingsAccountEntry.getAccountID() + "," + savingsAccountEntry.getCurrentBalance() + "," + savingsAccountEntry.getInterestRate() + "\n"); // Writes the SavingsAccount data to the file
                }
                else{ // If the account is not a savings account, it is a BankAccount
                    write.write(accountEntry.getAccountID() + "," + accountEntry.getCurrentBalance() + "\n"); // Writes the BankAccount data to the file
                }
            }    
        }
        catch(IOException e){ // If the attempt to write fails, an exception is thrown
            throw new IOException();
        }
        finally{ // Finally, the file and the writer object is closed
            write.close(); 
        }
    }
}
