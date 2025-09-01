package com.airtel;

public class Plan {
    private String name;
    private double price;
    private double dataGB;
    private int callMinutes;
    private int smsCount;

    public Plan(String name, double price, double dataGB, int callMinutes, int smsCount) {
        this.name = name;
        this.price = price;
        this.dataGB = dataGB;
        this.callMinutes = callMinutes;
        this.smsCount = smsCount;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDataGB() { return dataGB; }
    public int getCallMinutes() { return callMinutes; }
    public int getSmsCount() { return smsCount; }

    @Override
    public String toString() {
        return name + " - ₹" + price + " (" + dataGB + "GB, " + callMinutes + " min, " + smsCount + " SMS)";
    }
}
