package com.airtel;

import java.time.LocalDateTime;

public class Transaction {
    private int userId;
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int userId, String type, double amount, LocalDateTime timestamp) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getUserId() { return userId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + ": ₹" + amount;
    }
}
