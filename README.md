# Java Banking Application

A Java-based Banking Application developed using **Java Swing and Object-Oriented Programming (OOP)**. The application provides a graphical user interface for managing customer accounts and performing basic banking operations.

## About the Project

The Banking Application is designed to simplify basic banking operations through a user-friendly graphical interface. It supports two roles: Admin and User, each with different functionalities.

The project demonstrates the practical implementation of Java programming concepts, including classes, objects, encapsulation, constructors, collections, conditional statements, and exception handling.

## Features

### Admin Module
- Create new customer accounts.
- View all customer accounts.
- Update customer names.
- Delete customer accounts.
- Automatically generate account numbers.

### User Module
- Deposit money.
- Withdraw money.
- Transfer funds between accounts.
- Check account balance.
- View transaction history.

### Additional Features
- Input validation for banking operations.
- Prevention of withdrawals exceeding the available balance.
- Validation of account numbers.
- Separate transaction histories for individual accounts.
- Graphical interface built using Java Swing.

## Technologies Used

- **Programming Language:** Java
- **GUI Framework:** Java Swing
- **Data Structures:** ArrayList
- **Concepts:** Object-Oriented Programming, Encapsulation, Constructors, Collections, Conditional Statements, Loops, and Exception Handling
- **Development Tools:** Java JDK and a Java-compatible IDE

## Project Structure

```text
Banking-Application/
│
├── BankingGUI.java
│   └── Main application window and login interface
│
├── BankAccount.java
│   └── Account details, deposits, withdrawals,
│       fund transfers, and transaction history
│
├── Bank.java
│   └── Account creation, searching, retrieval,
│       and deletion
│
├── AdminDashboard.java
│   └── Administrative account management
│
├── UserDashboard.java
│   └── Customer banking operations
│
└── README.md
```

*Note: The classes may be organized in a single Java source file rather than separate files, depending on your repository's actual structure.*

## How to Run

### Prerequisites
- Java Development Kit (JDK) installed.
- A Java-compatible IDE, such as VS Code, Eclipse, or IntelliJ IDEA.

### Steps

1. Clone this repository:

   ```bash
   git clone YOUR_REPOSITORY_URL
   ```

2. Open the project in your Java IDE.

3. Ensure the Java source files are configured correctly.

4. Run the file containing the `main()` method, which is `BankingGUI` in this project.

5. The Banking Application's main window will appear.

6. Select the Admin or User option to access the corresponding functionality.

## Admin Login

The current implementation uses hardcoded Admin credentials for demonstration purposes.
