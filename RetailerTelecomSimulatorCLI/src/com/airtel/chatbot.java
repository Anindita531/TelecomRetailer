package com.airtel;

import java.util.Scanner;

public class chatbot {
    private User user;

    public chatbot(User user) {
        this.user = user;
    }

    public void startChat() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello " + user.getName() + "! I am AirtelBot. How can I help you?");
        String input;
        while(true) {
            System.out.println("\nOptions: 1.Check Balance 2.Check Data/Call/SMS 3.Recharge 4.Plan Recommendation 5.Exit");
            System.out.print("Enter choice: ");
            input = sc.nextLine();
            switch(input) {
                case "1":
                    System.out.println("Wallet Balance: ₹" + user.getWallet().getBalance());
                    break;
                case "2":
                    System.out.println("Data used: " + user.getDataUsage() + " GB");
                    System.out.println("Call minutes used: " + user.getCallMinutes());
                    System.out.println("SMS sent: " + user.getSmsSent());
                    break;
                case "3":
                    System.out.print("Enter recharge amount: ₹");
                    double amt = Double.parseDouble(sc.nextLine());
                    user.getWallet().deposit(amt);
                    break;
                case "4":
                    recommendPlan();
                    break;
                case "5":
                    System.out.println("Thank you for using AirtelBot. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void recommendPlan() {
        double data = user.getDataUsage();
        int calls = user.getCallMinutes();
        int sms = user.getSmsSent();

        System.out.println("Based on your usage: Data=" + data + "GB, Calls=" + calls + " min, SMS=" + sms);
        if(data > 5) System.out.println("Recommended Plan: Airtel 599 (10GB, 500min, 100 SMS)");
        else System.out.println("Recommended Plan: Airtel 299 (5GB, 300min, 50 SMS)");
    }
}
