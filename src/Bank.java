import java.util.ArrayList;

public class Bank {

    private ArrayList<BankAccount> accounts;
    private int nextAccountNumber;

    public Bank() {

        accounts = new ArrayList<>();

        nextAccountNumber = 1001;
    }

    public BankAccount createAccount(
            String name,
            double initialBalance) {

        if (name.isEmpty() || initialBalance < 0) {
            return null;
        }

        BankAccount account =
                new BankAccount(
                        nextAccountNumber,
                        name,
                        initialBalance
                );

        accounts.add(account);

        nextAccountNumber++;

        return account;
    }

    public BankAccount findAccount(int accountNumber) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber()
                    == accountNumber) {

                return account;
            }
        }

        return null;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public boolean deleteAccount(int accountNumber) {

        BankAccount account =
                findAccount(accountNumber);

        if (account == null) {
            return false;
        }

        accounts.remove(account);

        return true;
    }
}