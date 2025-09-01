package com.airtel;

public class User {
    private int id;
    private String name;
    private Wallet wallet;
    private Plan currentPlan;
    private double dataUsage;
    private int callMinutes;
    private int smsSent;

    public User(int id, String name, Plan plan, double balance, double dataUsage, int callMinutes, int smsSent) {
        this.id = id;
        this.name = name;
        this.currentPlan = plan;
        this.wallet = new Wallet(id, balance);
        this.dataUsage = dataUsage;
        this.callMinutes = callMinutes;
        this.smsSent = smsSent;
    }

    public void switchPlan(Plan newPlan) {
        if(wallet.getBalance() >= newPlan.getPrice()) {
            wallet.withdraw(newPlan.getPrice());
            currentPlan = newPlan;
            System.out.println("Plan switched successfully to " + newPlan.getName());
        } else {
            System.out.println("Insufficient balance to switch plan!");
        }
    }

    public Plan getCurrentPlan() { return currentPlan; }
    public Wallet getWallet() { return wallet; }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getDataUsage() { return dataUsage; }
    public int getCallMinutes() { return callMinutes; }
    public int getSmsSent() { return smsSent; }

    public void addUsage(double data, int calls, int sms) {
        dataUsage += data;
        callMinutes += calls;
        smsSent += sms;
    }
}
