package edu.sdccd.cisc190.interfaces;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Setup extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Name Input");
        Label nameLabel = new Label("User Name");
        TextField nameField = new TextField();
        nameField.setPromptText("Your Name");

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String userName = nameField.getText();
            displayMessage("Hello, " + userName + "!");
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(nameLabel, nameField, submitButton);

        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Name");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
