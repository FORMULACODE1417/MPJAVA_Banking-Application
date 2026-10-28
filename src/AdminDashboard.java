import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private Bank bank;

    public AdminDashboard(Bank bank) {

        this.bank = bank;

        setTitle("Admin Dashboard");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel(
                "ADMIN DASHBOARD",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        title.setForeground(
                new Color(30, 60, 120)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 20, 10
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
                new GridLayout(
                        5,
                        1,
                        15,
                        15
                )
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        80,
                        40,
                        80
                )
        );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );

        JButton createButton =
                new JButton("Create Account");

        JButton viewButton =
                new JButton("View Accounts");

        JButton updateButton =
                new JButton("Update Account");

        JButton deleteButton =
                new JButton("Delete Account");

        JButton logoutButton =
                new JButton("Logout");

        styleButton(createButton);
        styleButton(viewButton);
        styleButton(updateButton);
        styleButton(deleteButton);
        styleButton(logoutButton);

        buttonPanel.add(createButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);


        // CREATE ACCOUNT
        createButton.addActionListener(e -> {
            createAccount();
        });


        // VIEW ACCOUNTS
        viewButton.addActionListener(e -> {
            viewAccounts();
        });


        // UPDATE ACCOUNT
        updateButton.addActionListener(e -> {
            updateAccount();
        });


        // DELETE ACCOUNT
        deleteButton.addActionListener(e -> {
            deleteAccount();
        });


        // LOGOUT
        logoutButton.addActionListener(e -> {

            dispose();

            new BankingGUI();
        });


        setVisible(true);
    }


    // BUTTON DESIGN
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
                        12,
                        20,
                        12,
                        20
                )
        );
    }


    // CREATE ACCOUNT
    private void createAccount() {

        String name =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Customer Name:"
                );

        if (name == null ||
                name.trim().isEmpty()) {

            return;
        }

        String balanceInput =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Initial Balance:"
                );

        if (balanceInput == null) {
            return;
        }

        try {

            double balance =
                    Double.parseDouble(
                            balanceInput
                    );

            BankAccount account =
                    bank.createAccount(
                            name,
                            balance
                    );

            if (account != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Created Successfully!\n" +
                                "Account Number: " +
                                account.getAccountNumber()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Account Details"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a Valid Balance"
            );
        }
    }


    // VIEW ACCOUNTS
    private void viewAccounts() {

        if (bank.getAccounts().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No Accounts Available"
            );

            return;
        }

        StringBuilder details =
                new StringBuilder();

        for (BankAccount account :
                bank.getAccounts()) {

            details.append(
                    "Account Number: "
            );

            details.append(
                    account.getAccountNumber()
            );

            details.append(
                    "\nCustomer Name: "
            );

            details.append(
                    account.getCustomerName()
            );

            details.append(
                    "\nBalance: Rs."
            );

            details.append(
                    account.getBalance()
            );

            details.append(
                    "\n---------------------------\n"
            );
        }

        JTextArea textArea =
                new JTextArea(
                        details.toString()
                );

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        textArea
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        380,
                        250
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "All Accounts",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // UPDATE ACCOUNT
    private void updateAccount() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Account Number:"
                );

        if (input == null) {
            return;
        }

        try {

            int accountNumber =
                    Integer.parseInt(
                            input
                    );

            BankAccount account =
                    bank.findAccount(
                            accountNumber
                    );

            if (account == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Not Found"
                );

                return;
            }

            String newName =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter New Customer Name:"
                    );

            if (newName != null &&
                    !newName.trim().isEmpty()) {

                account.setCustomerName(
                        newName
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Account Updated Successfully"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Account Number"
            );
        }
    }


    // DELETE ACCOUNT
    private void deleteAccount() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Account Number:"
                );

        if (input == null) {
            return;
        }

        try {

            int accountNumber =
                    Integer.parseInt(
                            input
                    );

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this account?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            boolean deleted =
                    bank.deleteAccount(
                            accountNumber
                    );

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Deleted Successfully"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Not Found"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Account Number"
            );
        }
    }
}