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

Retailer Telecom Simulator GUI

A JavaFX-based desktop application for managing users, plans, and transactions in a telecom retail system.

Prerequisites

JDK 17+ installed (Download here
)

JavaFX SDK 24.0.2 downloaded and extracted (Download here
)

MySQL Connector/J jar if using MySQL database

IDE (Optional): IntelliJ IDEA or VSCode

Folder Structure
RetailerTelecomSimulatoGUI/
├─ lib/
│  ├─ javafx-sdk-24.0.2/        # JavaFX SDK folder
│  └─ mysql-connector-j-9.3.0.jar
├─ src/
│  └─ com/airtel/               # Source code
├─ README.md

1. Compile via Command Prompt

Open a command prompt in the src folder:

javac --module-path "D:\Learning From Zero\RetailerTelecomSimulatoGUI\lib\javafx-sdk-24.0.2\lib" --add-modules javafx.controls,javafx.fxml com\airtel\*.java


--module-path points to the JavaFX lib folder.

--add-modules specifies the JavaFX modules you are using (controls and fxml).

2. Run via Command Prompt
java --module-path "D:\Learning From Zero\RetailerTelecomSimulatoGUI\lib\javafx-sdk-24.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp ".;D:\Learning From Zero\RetailerTelecomSimulatoGUI\lib\mysql-connector-j-9.3.0.jar" com.airtel.MainFX


-cp includes both compiled classes (.) and external libraries (MySQL connector).

Replace com.airtel.MainFX with the fully-qualified main class if different.

3. Run in IntelliJ IDEA

Add JavaFX SDK:

File → Project Structure → Libraries → + → Add javafx-sdk-24.0.2/lib

Set VM options:

Run → Edit Configurations → VM options:

--module-path D:\Learning From Zero\RetailerTelecomSimulatoGUI\lib\javafx-sdk-24.0.2\lib --add-modules javafx.controls,javafx.fxml


Add MySQL Connector to Project Libraries.

4. Run in VSCode

Install Java Extension Pack.

Open folder, then add JavaFX SDK in settings.json or .classpath.

Use the Run Configuration with VM options:

--module-path D:\Learning From Zero\RetailerTelecomSimulatoGUI\lib\javafx-sdk-24.0.2\lib --add-modules javafx.controls,javafx.fxml

5. Notes

Make sure absolute paths are used in command-line instructions.

Use compatible JDK version (17 or above recommended).

Ensure the MySQL JDBC driver is included in the classpath if database functionality is required.

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



