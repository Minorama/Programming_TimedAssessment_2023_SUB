import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsException;
import java.util.Scanner;
import java.io.File;
//import java.io.FileWriter;
import java.io.FileNotFoundException;


public class Bank {

    private BankAccount[] accounts = new BankAccount[100];

    
    public void readAccounts() throws FileNotFoundException {
        File file = new File("accounts.txt");
        Scanner scanner = new Scanner(file);
        int count = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int accountID = Integer.parseInt(parts[0]);
            double currentBalance = Double.parseDouble(parts[1]);
            if (parts.length == 3) {
                double interestRate = Double.parseDouble(parts[2]);
                accounts[count] = new SavingsAccount(accountID, currentBalance, interestRate);
                count++;    
            }
            else{
                accounts[count] = new BankAccount(accountID, currentBalance);
                count++;
            }
        }
        scanner.close();
    }

    
    public BankAccount[] getAccounts() {
        return accounts;
    }
    

    /*
    public ?? getAccount(??) throws ?? {
        ??;
    }
    */

    /*
    public ?? getAccountIndex(??) throws ?? {
        ??;
    }
    */

    /*
    public ?? getNextAccountID(){
        ??;
    }
    */

    /*
    public ?? openBankAccount() {
        ??;
    }
    */

    /*
    public ?? openSavingsAccount() {
        ??;
    }
    */

    /*
    public ?? closeAccount(??) throws ?? {
        ??;
    }
    */

    /*
    public ?? transferFunds(??) throws ?? {
        ??;
    }
    */

    /*
    public ?? saveAccounts(??) throws ?? {
        ??;
    }
    */


}
