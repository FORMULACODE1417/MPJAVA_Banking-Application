import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    private Bank bank;
    private BankAccount account;

    public UserDashboard(Bank bank, BankAccount account) {

        this.bank = bank;
        this.account = account;

        setTitle("User Dashboard - " + account.getCustomerName());
        setSize(550, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel(
                "WELCOME, " + account.getCustomerName().toUpperCase(),
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(30, 60, 120));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 10, 10
                )
        );

        JLabel accountInfo = new JLabel(
                "Account Number: " + account.getAccountNumber(),
                SwingConstants.CENTER
        );

        accountInfo.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(245, 247, 250));

        topPanel.add(title);
        topPanel.add(accountInfo);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(
                new GridLayout(6, 1, 15, 15)
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 80, 35, 80
                )
        );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );

        JButton depositButton =
                new JButton("Deposit Money");

        JButton withdrawButton =
                new JButton("Withdraw Money");

        JButton transferButton =
                new JButton("Transfer Funds");

        JButton balanceButton =
                new JButton("Check Balance");

        JButton historyButton =
                new JButton("Transaction History");

        JButton logoutButton =
                new JButton("Logout");

        styleButton(depositButton);
        styleButton(withdrawButton);
        styleButton(transferButton);
        styleButton(balanceButton);
        styleButton(historyButton);
        styleButton(logoutButton);

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        depositButton.addActionListener(e -> depositMoney());

        withdrawButton.addActionListener(e -> withdrawMoney());

        transferButton.addActionListener(e -> transferMoney());

        balanceButton.addActionListener(e -> checkBalance());

        historyButton.addActionListener(e -> showHistory());

        logoutButton.addActionListener(e -> {

            dispose();

            new BankingGUI();
        });

        setVisible(true);
    }


    private void styleButton(JButton button) {

        button.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        button.setFocusPainted(false);

        button.setBackground(
                new Color(40, 90, 170)
        );

        button.setForeground(Color.WHITE);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 20, 12, 20
                )
        );
    }


    private void depositMoney() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Deposit Amount:"
                );

        if (input == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(input);

            if (account.deposit(amount)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Deposit Successful!\n" +
                                "New Balance: Rs." +
                                account.getBalance()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Deposit Amount"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a Valid Amount"
            );
        }
    }


    private void withdrawMoney() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Withdrawal Amount:"
                );

        if (input == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(input);

            if (account.withdraw(amount)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Withdrawal Successful!\n" +
                                "New Balance: Rs." +
                                account.getBalance()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Insufficient Balance or Invalid Amount"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a Valid Amount"
            );
        }
    }


    private void transferMoney() {

        String receiverInput =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Receiver Account Number:"
                );

        if (receiverInput == null) {
            return;
        }

        try {

            int receiverNumber =
                    Integer.parseInt(receiverInput);

            BankAccount receiver =
                    bank.findAccount(receiverNumber);

            if (receiver == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Receiver Account Not Found"
                );

                return;
            }

            if (receiver == account) {

                JOptionPane.showMessageDialog(
                        this,
                        "You Cannot Transfer Money to Your Own Account"
                );

                return;
            }

            String amountInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Transfer Amount:"
                    );

            if (amountInput == null) {
                return;
            }

            double amount =
                    Double.parseDouble(amountInput);

            if (account.transfer(receiver, amount)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Transfer Successful!\n" +
                                "Remaining Balance: Rs." +
                                account.getBalance()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Insufficient Balance or Invalid Amount"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Valid Details"
            );
        }
    }


    private void checkBalance() {

        JOptionPane.showMessageDialog(
                this,
                "Account Number: " +
                        account.getAccountNumber() +
                        "\nCustomer Name: " +
                        account.getCustomerName() +
                        "\nCurrent Balance: Rs." +
                        account.getBalance()
        );
    }


    private void showHistory() {

        StringBuilder history =
                new StringBuilder();

        history.append("Account Number: ")
                .append(account.getAccountNumber())
                .append("\n");

        history.append("Customer Name: ")
                .append(account.getCustomerName())
                .append("\n\n");

        history.append("TRANSACTION HISTORY\n");
        history.append("-----------------------------\n");

        for (String transaction :
                account.getTransactionHistory()) {

            history.append(transaction)
                    .append("\n");
        }

        JTextArea textArea =
                new JTextArea(history.toString());

        textArea.setEditable(false);

        textArea.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(380, 250)
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Transaction History",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}