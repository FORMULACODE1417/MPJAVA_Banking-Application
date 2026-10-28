import javax.swing.*;
import java.awt.*;

public class BankingGUI extends JFrame {

    public static Bank bank = new Bank();

    public BankingGUI() {

        setTitle("Banking Application");
        setSize(520, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel(
                "BANKING APPLICATION",
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
                        30, 10, 20, 10
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
                new GridLayout(
                        3,
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

        JButton adminButton =
                new JButton("Admin Login");

        JButton userButton =
                new JButton("User Login");

        JButton exitButton =
                new JButton("Exit");

        styleButton(adminButton);
        styleButton(userButton);
        styleButton(exitButton);

        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);
        buttonPanel.add(exitButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);


        // ADMIN LOGIN
        adminButton.addActionListener(e -> {
            adminLogin();
        });


        // USER LOGIN
        userButton.addActionListener(e -> {
            userLogin();
        });


        // EXIT
        exitButton.addActionListener(e -> {
            System.exit(0);
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


    // ADMIN LOGIN
    private void adminLogin() {

        JTextField usernameField =
                new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        Object[] fields = {

                "Username:",
                usernameField,

                "Password:",
                passwordField
        };

        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Admin Login",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (option == JOptionPane.OK_OPTION) {

            String username =
                    usernameField.getText();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (username.equals("admin")
                    && password.equals("1234")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Admin Login Successful!"
                );

                new AdminDashboard(bank);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password"
                );
            }
        }
    }


    // USER LOGIN
    private void userLogin() {

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
                    Integer.parseInt(input);

            BankAccount account =
                    bank.findAccount(accountNumber);

            if (account == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Not Found"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!\nWelcome "
                                + account.getCustomerName()
                );

                new UserDashboard(
                        bank,
                        account
                );

                dispose();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please Enter a Valid Account Number"
            );
        }
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                BankingGUI::new
        );
    }
}