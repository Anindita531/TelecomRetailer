package com.airtel;

import java.util.Scanner;

public class MainCLI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\nLogin as: 1. User  2. Admin  3. Exit");
            String role = sc.nextLine();

            if(role.equals("1")) {
                System.out.print("Enter User ID: ");
                int id = Integer.parseInt(sc.nextLine());

                // Create dummy user with default plan
                User user = new User(id, "User" + id, PlanManager.getAllPlans().get(0), 1000, 0,0,0);

                while(true) {
                    System.out.println("\nOptions: 1.Balance 2.Usage 3.Recharge 4.Current Plan 5.View All Plans 6.Switch Plan 7.Transactions 8.Logout");
                    String choice = sc.nextLine();

                    switch(choice) {
                        case "1": System.out.println("Balance: ₹" + user.getWallet().getBalance()); break;
                        case "2": System.out.println("Data: " + user.getDataUsage() + "GB, Calls: " + user.getCallMinutes() + ", SMS: " + user.getSmsSent()); break;
                        case "3":
                            System.out.print("Enter recharge amount: ₹");
                            double amt = Double.parseDouble(sc.nextLine());
                            user.getWallet().deposit(amt);
                            break;
                        case "4": System.out.println("Current Plan: " + user.getCurrentPlan()); break;
                        case "5": 
                            System.out.println("Available Plans:");
                            for(Plan p : PlanManager.getAllPlans()) System.out.println(p);
                            break;
                        case "6":
                            System.out.print("Enter plan name to switch: ");
                            String planName = sc.nextLine();
                            Plan newPlan = PlanManager.getPlanByName(planName);
                            if(newPlan != null) user.switchPlan(newPlan);
                            else System.out.println("Plan not found!");
                            break;
                        case "7":
                            user.getWallet().showTransactions();
                            break;
                        case "8":
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    if(choice.equals("8")) break;
                }

            } else if(role.equals("2")) {
                System.out.print("Enter Admin password: ");
                String pass = sc.nextLine();
                if(!pass.equals("admin123")) {
                    System.out.println("Invalid password!");
                    continue;
                }

                while(true) {
                    System.out.println("\nAdmin Options: 1.View Transactions 2.View Plans 3.Add Plan 4.Logout");
                    String choice = sc.nextLine();
                    switch(choice) {
                        case "1": Admin.viewAllTransactions(); break;
                        case "2": Admin.viewPlans(); break;
                        case "3":
                            System.out.print("Enter Plan Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Price: ");
                            double price = Double.parseDouble(sc.nextLine());
                            System.out.print("Enter Data GB: ");
                            double data = Double.parseDouble(sc.nextLine());
                            System.out.print("Enter Call Minutes: ");
                            int calls = Integer.parseInt(sc.nextLine());
                            System.out.print("Enter SMS Count: ");
                            int sms = Integer.parseInt(sc.nextLine());
                            Admin.addPlan(name, price, data, calls, sms);
                            break;
                        case "4":
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    if(choice.equals("4")) break;
                }

            } else if(role.equals("3")) break;
            else System.out.println("Invalid option.");
        }

        sc.close();
    }
}
