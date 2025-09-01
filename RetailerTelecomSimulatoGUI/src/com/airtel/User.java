package com.airtel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private Wallet wallet;
    private String planName;
    private double dataUsage;
    private int callMinutes;
    private int smsSent;

    public User(int id, String name, String planName, double balance, double dataUsage, int callMinutes, int smsSent) {
        this.id = id;
        this.name = name;
        this.planName = planName;
        this.wallet = new Wallet(id, balance);
        this.dataUsage = dataUsage;
        this.callMinutes = callMinutes;
        this.smsSent = smsSent;
    }
// In User.java
public void setPlanName(String planName) {
    this.planName = planName;
    updateBalanceDB(); // optional: save the new plan to DB
}

    public static User loadUser(int userId) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("plan_name"),
                        rs.getDouble("balance"),
                        rs.getDouble("data_usage"),
                        rs.getInt("call_minutes"),
                        rs.getInt("sms_sent")
                );
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void updateBalanceDB() {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET balance=?, data_usage=?, call_minutes=?, sms_sent=? WHERE id=?");
            ps.setDouble(1, wallet.getBalance());
            ps.setDouble(2, dataUsage);
            ps.setInt(3, callMinutes);
            ps.setInt(4, smsSent);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Wallet getWallet() { return wallet; }
    public String getPlanName() { return planName; }
    public double getDataUsage() { return dataUsage; }
    public int getCallMinutes() { return callMinutes; }
    public int getSmsSent() { return smsSent; }

    @Override
    public String toString() {
        return "ID:" + id + ", Name:" + name + ", Plan:" + planName + ", Balance:₹" + wallet.getBalance();
    }
}
