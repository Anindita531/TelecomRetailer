package com.airtel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFX extends Application {

    private Stage primaryStage;
    private Scene loginScene;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Airtel Simulator");

        createLoginScene();

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void createLoginScene() {
        Label label = new Label("Select Role:");
        RadioButton userBtn = new RadioButton("User");
        RadioButton adminBtn = new RadioButton("Admin");
        ToggleGroup group = new ToggleGroup();
        userBtn.setToggleGroup(group);
        adminBtn.setToggleGroup(group);

        TextField userIdField = new TextField();
        userIdField.setPromptText("Enter User ID");

        Button loginBtn = new Button("Login");

        VBox layout = new VBox(10, label, userBtn, adminBtn, userIdField, loginBtn);
        layout.setSpacing(10);
        layout.setPadding(new javafx.geometry.Insets(20));

        loginScene = new Scene(layout, 400, 300);

        loginBtn.setOnAction(e -> {
            try {
                if(userBtn.isSelected()) {
                    int userId = Integer.parseInt(userIdField.getText());
                    User currentUser = User.loadUser(userId);
                    if(currentUser == null) {
                        showAlert("Error", "User not found.");
                        return;
                    }
                    UserPanel userPanel = new UserPanel(currentUser );
                    primaryStage.setScene(userPanel.getScene());

                } else if(adminBtn.isSelected()) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Admin Login");
                    dialog.setHeaderText("Enter Admin Password:");
                    dialog.showAndWait().ifPresent(password -> {
                        if(password.equals("admin123")) {
                            AdminPanel adminPanel = new AdminPanel(primaryStage );
                            primaryStage.setScene(adminPanel.getScene());
                        } else {
                            showAlert("Error", "Invalid Admin Password!");
                        }
                    });
                } else {
                    showAlert("Error", "Select a role.");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
                showAlert("Error", "Something went wrong.");
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
