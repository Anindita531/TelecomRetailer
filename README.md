# Retailer Telecom Simulator

A Java-based CLI and GUI application that simulates a telecom management system, including user management, plan management, wallet transactions, and admin controls.

---

## Features

### User Features (CLI & GUI)
- View wallet balance
- View usage: Data, Call Minutes, SMS
- Recharge wallet
- View transaction history
- View current plan
- Recommended plan based on usage
- Logout

### Admin Features (CLI & GUI)
- Add new users
- Add or update plans
- View all users
- View all transactions
- Logout

---

## Project Structure
com.airtel
├── Admin.java # Admin logic (CLI)
├── AdminPanel.java # Admin GUI
├── Chatbot.java # CLI-based user chatbot
├── ChatbotGUI.java # GUI-based chatbot
├── Main.java # CLI entry point
├── MainFX.java # GUI entry point
├── User.java # User class with DB operations
├── UserPanel.java # GUI user panel
├── Wallet.java # Wallet management & transactions
├── Transaction.java # Transaction model
├── Plan.java # Plan model
├── Database.java # Database connection
└── ...


---

## Prerequisites

- Java 8 or higher
- JavaFX SDK (for GUI)
- MySQL (or any JDBC-compatible database)
- IDE: IntelliJ, Eclipse, or VS Code

---

## Database Setup

1. Create database `telecom_simulator`
2. Create `users` table:

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    plan_name VARCHAR(50),
    balance DOUBLE,
    data_usage DOUBLE DEFAULT 0,
    call_minutes INT DEFAULT 0,
    sms_sent INT DEFAULT 0
);


Create plans table:

CREATE TABLE plans (
    name VARCHAR(50) PRIMARY KEY,
    price DOUBLE,
    data_limit DOUBLE,
    call_limit INT,
    sms_limit INT
);


Create transactions table:

CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    type VARCHAR(50),
    amount DOUBLE,
    timestamp DATETIME
);


Update Database.java with your DB credentials.

How to Run
CLI
javac com/airtel/*.java
java com.airtel.Main

GUI
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml com/airtel/*.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml com.airtel.MainFX

Usage

User Login

Enter User ID

Choose options: Balance, Usage, Recharge, Plan Recommendation, Transactions, Logout

Admin Login

Enter password (admin123)

Manage users and plans

View all transactions

Logout

Notes

Transactions are automatically logged and saved in the database.

Plan recommendations are based on user usage.

GUI uses JavaFX for interactive dialogs and panels.

Author

Anindita Ghosh

License

This project is for educational purposes.



