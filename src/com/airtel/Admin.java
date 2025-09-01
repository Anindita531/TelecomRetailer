package com.airtel;

public class Admin {

    public static void viewAllTransactions() {
        for(Transaction t : Wallet.getAllTransactions()) {
            System.out.println(t);
        }
    }

    public static void addPlan(String name, double price, double dataGB, int callMinutes, int smsCount) {
        PlanManager.addPlan(new Plan(name, price, dataGB, callMinutes, smsCount));
        System.out.println("Plan added successfully!");
    }

    public static void viewPlans() {
        for(Plan p : PlanManager.getAllPlans()) {
            System.out.println(p);
        }
    }
}
