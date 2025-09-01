package com.airtel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private double balance;
    private int userId;
    private static List<Transaction> transactions = new ArrayList<>();

    public Wallet(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        balance += amount;
        logTransaction("Deposit", amount);
    }

    public boolean withdraw(double amount) {
        if(balance >= amount) {
            balance -= amount;
            logTransaction("Withdraw", amount);
            return true;
        }
        return false;
    }

    private void logTransaction(String type, double amount) {
        Transaction t = new Transaction(userId, type, amount, LocalDateTime.now());
        transactions.add(t);
        // Optional: save to DB
        try(Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO transactions (user_id, type, amount, timestamp) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getUserId());
            ps.setString(2, t.getType());
            ps.setDouble(3, t.getAmount());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(t.getTimestamp()));
            ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
    }

    public void showTransactions() {
        boolean found = false;
        for(Transaction t : transactions) {
            if(t.getUserId() == userId) {
                System.out.println(t);
                found = true;
            }
        }
        if(!found) System.out.println("No transactions yet.");
    }

    public static List<Transaction> getAllTransactions() { return transactions; }
}
