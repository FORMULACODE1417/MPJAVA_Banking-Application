import java.util.ArrayList;

public class BankAccount {

    private int accountNumber;
    private String customerName;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(int accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;

        transactionHistory = new ArrayList<>();

        transactionHistory.add(
                "Account created with balance: Rs." + balance
        );
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean deposit(double amount) {

        if (amount <= 0) {
            return false;
        }

        balance = balance + amount;

        transactionHistory.add(
                "Deposited: Rs." + amount
        );

        return true;
    }

    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance = balance - amount;

        transactionHistory.add(
                "Withdrawn: Rs." + amount
        );

        return true;
    }

    public boolean transfer(BankAccount receiver, double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance = balance - amount;

        receiver.balance =
                receiver.balance + amount;

        transactionHistory.add(
                "Transferred Rs." + amount +
                        " to Account " +
                        receiver.accountNumber
        );

        receiver.transactionHistory.add(
                "Received Rs." + amount +
                        " from Account " +
                        accountNumber
        );

        return true;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}