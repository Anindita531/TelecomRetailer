package com.airtel;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

public class UserPanel {
    private User user;
    private Wallet wallet;
    private Scene scene;

    public UserPanel(User user) {
        this.user = user;
        this.wallet = new Wallet(user.getId(), user.getWallet().getBalance());
        createScene();
    }

    public Scene getScene() {
        return scene;
    }

    private void createScene() {
        Label welcomeLabel = new Label("Welcome, " + user.getName() + "!");
        TextArea menuArea = new TextArea();
        menuArea.setText(getMenuText());
        menuArea.setEditable(false);
        menuArea.setWrapText(true);

        TextField choiceField = new TextField();
        choiceField.setPromptText("Enter option number");

        Button submitBtn = new Button("Submit");

        VBox layout = new VBox(10, welcomeLabel, menuArea, choiceField, submitBtn);
        layout.setPadding(new Insets(15));

        submitBtn.setOnAction(e -> {
            String choice = choiceField.getText();
            switch(choice) {
                case "1": showAlert("Balance", "₹" + wallet.getBalance()); break;
                case "2": showAlert("Usage", "Data: " + user.getDataUsage() + " GB\nCalls: " +
                        user.getCallMinutes() + " min\nSMS: " + user.getSmsSent()); break;
                case "3": recharge(); break;
                case "4": showAlert("Current Plan", user.getPlanName()); break;
                case "5": showAlert("Available Plans", getAllPlans()); break;
                case "6": switchPlan(); break;
                case "7": showTransactions(); break;
                case "8": choiceField.getScene().getWindow().hide(); break; // Logout
                default: showAlert("Error", "Invalid choice!"); break;
            }
            menuArea.setText(getMenuText()); // refresh menu display
            choiceField.clear();
        });

        scene = new Scene(layout, 400, 400);
    }

    private String getMenuText() {
        return "Options:\n" +
               "1.Balance\n" +
               "2.Usage\n" +
               "3.Recharge\n" +
               "4.Current Plan\n" +
               "5.View All Plans\n" +
               "6.Switch Plan\n" +
               "7.Transactions\n" +
               "8.Logout";
    }

    private void recharge() {
        TextInputDialog amtDialog = new TextInputDialog("0");
        amtDialog.setTitle("Recharge");
        amtDialog.setHeaderText("Enter recharge amount:");
        double amt = Double.parseDouble(amtDialog.showAndWait().orElse("0"));
        wallet.deposit(amt);
        showAlert("Recharge", "₹" + amt + " deposited successfully!");
    }

    private void switchPlan() {
        TextInputDialog planDialog = new TextInputDialog(user.getPlanName());
        planDialog.setTitle("Switch Plan");
        planDialog.setHeaderText("Enter new plan name:");
        String newPlan = planDialog.showAndWait().orElse(user.getPlanName());
        user.setPlanName(newPlan);
        showAlert("Plan Updated", "Your new plan: " + newPlan);
    }

    private void showTransactions() {
        List<Transaction> all = Admin.getAllTransactions();
        StringBuilder sb = new StringBuilder();
        for(Transaction t : all) {
            if(t.getUserId() == user.getId()) sb.append(t).append("\n");
        }
        showAlert("Transactions", sb.length() == 0 ? "No transactions yet." : sb.toString());
    }

    private String getAllPlans() {
        return "Airtel 299, Airtel 399, Airtel 599, Airtel 799";
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
