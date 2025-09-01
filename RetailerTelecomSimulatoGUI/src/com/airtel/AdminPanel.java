package com.airtel;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPanel {
    private Scene scene;
    private Stage stage;

    public AdminPanel(Stage stage) {
        this.stage = stage;
        createScene();
    }

    public Scene getScene() {
        return scene;
    }

    private void createScene() {
        Label titleLabel = new Label("Admin Panel");

        Button addUserBtn = new Button("Add User");
        Button addPlanBtn = new Button("Add/Update Plan");
        Button viewUsersBtn = new Button("View All Users");
        Button viewTransactionsBtn = new Button("View All Transactions");
        Button logoutBtn = new Button("Logout");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(titleLabel, addUserBtn, addPlanBtn, viewUsersBtn, viewTransactionsBtn, logoutBtn);

        // --- Button actions ---
        addUserBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add User");
            dialog.setHeaderText("Enter new user name:");
            dialog.showAndWait().ifPresent(name -> {
                TextInputDialog planDialog = new TextInputDialog();
                planDialog.setTitle("Plan");
                planDialog.setHeaderText("Enter Plan Name for user:");
                planDialog.showAndWait().ifPresent(plan -> {
                    TextInputDialog balanceDialog = new TextInputDialog();
                    balanceDialog.setTitle("Initial Balance");
                    balanceDialog.setHeaderText("Enter Initial Balance:");
                    balanceDialog.showAndWait().ifPresent(balanceStr -> {
                        try {
                            double balance = Double.parseDouble(balanceStr);
                            Admin.addUser(name, plan, balance);
                            showAlert("Success", "User added successfully.");
                        } catch(Exception ex) {
                            showAlert("Error", "Invalid balance.");
                        }
                    });
                });
            });
        });

        addPlanBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add/Update Plan");
            dialog.setHeaderText("Enter Plan Name:");
            dialog.showAndWait().ifPresent(name -> {
                TextInputDialog priceDialog = new TextInputDialog();
                priceDialog.setTitle("Price");
                priceDialog.setHeaderText("Enter Plan Price:");
                priceDialog.showAndWait().ifPresent(priceStr -> {
                    try {
                        double price = Double.parseDouble(priceStr);
                        Plan plan = new Plan(name, price, 5, 300, 50); // default limits
                        Admin.addOrUpdatePlan(plan);
                        showAlert("Success", "Plan added/updated successfully.");
                    } catch(Exception ex) {
                        showAlert("Error", "Invalid price.");
                    }
                });
            });
        });

        viewUsersBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            for(User u : Admin.getAllUsers()) {
                sb.append(u).append("\n");
            }
            showAlert("All Users", sb.length() == 0 ? "No users." : sb.toString());
        });

        viewTransactionsBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            for(Transaction t : Admin.getAllTransactions()) {
                sb.append(t).append("\n");
            }
            showAlert("All Transactions", sb.length() == 0 ? "No transactions." : sb.toString());
        });

        logoutBtn.setOnAction(e -> {
            stage.setScene(new Scene(new VBox(), 400, 300)); // can redirect to login scene instead
        });

        scene = new Scene(layout, 450, 450);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
