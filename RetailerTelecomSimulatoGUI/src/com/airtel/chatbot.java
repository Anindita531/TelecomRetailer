package com.airtel;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Optional;

public class chatbot {

    private User user;
    private Stage parentStage; // The dashboard stage

    public chatbot(User user, Stage parentStage) {
        this.user = user;
        this.parentStage = parentStage;
    }

    public void startChat() {
        Platform.runLater(this::showMenu);
    }

    private void showMenu() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Airtel Chatbot");
        dialog.setHeaderText("Hello " + user.getName());
        dialog.setContentText("Choose an option:\n1. Balance\n2. Usage\n3. Recharge\n4. Plan Recommendation\n5. Logout/Exit");

        Optional<String> result = dialog.showAndWait();
        String input = result.orElse("5"); // default to exit if cancelled

        switch (input) {
            case "1":
                showAlert("Balance", "₹" + user.getWallet().getBalance());
                break;
            case "2":
                showAlert("Usage", "Data: " + user.getDataUsage() + "GB\nCalls: " + user.getCallMinutes() + "\nSMS: " + user.getSmsSent());
                break;
            case "3":
                handleRecharge();
                break;
            case "4":
                recommendPlan();
                break;
            case "5":
                handleLogout();
                return;
            default:
                showAlert("Error", "Invalid choice");
                break;
        }

        // Show menu again after action
        showMenu();
    }

    private void handleRecharge() {
        TextInputDialog amtDialog = new TextInputDialog("0");
        amtDialog.setTitle("Recharge");
        amtDialog.setHeaderText("Enter recharge amount:");
        Optional<String> amtResult = amtDialog.showAndWait();
        double amount = Double.parseDouble(amtResult.orElse("0"));
        user.getWallet().deposit(amount);
        user.updateBalanceDB();
        showAlert("Recharge", "₹" + amount + " deposited.");
    }

    private void recommendPlan() {
        double data = user.getDataUsage();
        String plan = data > 5 ? "Airtel 599 (10GB, 500min, 100SMS)" : "Airtel 299 (5GB, 300min, 50SMS)";
        showAlert("Recommendation", plan);
    }

    private void handleLogout() {
        Platform.runLater(() -> {
            parentStage.close(); // closes dashboard

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/airtel/view/Login.fxml"));
                Stage loginStage = new Stage();
                loginStage.setScene(new Scene(root));
                loginStage.setTitle("Login");
                loginStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}
