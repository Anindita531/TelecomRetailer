package com.airtel;

public class Plan {
    private String name;
    private double price;
    private double dataLimit;
    private int callLimit;
    private int smsLimit;

    public Plan(String name, double price, double dataLimit, int callLimit, int smsLimit) {
        this.name = name;
        this.price = price;
        this.dataLimit = dataLimit;
        this.callLimit = callLimit;
        this.smsLimit = smsLimit;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDataLimit() { return dataLimit; }
    public int getCallLimit() { return callLimit; }
    public int getSmsLimit() { return smsLimit; }

    @Override
    public String toString() {
        return "Plan [Name=" + name + ", Price=₹" + price + ", Data=" + dataLimit + "GB, Calls=" + callLimit + ", SMS=" + smsLimit + "]";
    }
}
