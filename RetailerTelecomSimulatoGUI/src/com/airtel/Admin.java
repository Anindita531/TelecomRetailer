package com.airtel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    public static List<Plan> getAllPlans() {
        List<Plan> plans = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM plans";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("data_limit"),
                        rs.getInt("call_limit"),
                        rs.getInt("sms_limit")
                );
                plans.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plans;
    }
    // Add user
    public static void addUser(String name, String plan, double balance) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO users (name, plan_name, balance, data_usage, call_minutes, sms_sent) VALUES (?, ?, ?, 0,0,0)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, plan);
            ps.setDouble(3, balance);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add or update plan
    public static void addOrUpdatePlan(Plan plan) {
        try (Connection conn = Database.getConnection()) {
            String sql = "REPLACE INTO plans (name, price, data_limit, call_limit, sms_limit) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, plan.getName());
            stmt.setDouble(2, plan.getPrice());
            stmt.setDouble(3, plan.getDataLimit());
            stmt.setInt(4, plan.getCallLimit());
            stmt.setInt(5, plan.getSmsLimit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Transactions
    public static void addTransaction(Transaction t) {
        Wallet.addTransaction(t);
    }

    public static List<Transaction> getAllTransactions() {
        return Wallet.getAllTransactions();
    }

    // Get all users
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(User.loadUser(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
