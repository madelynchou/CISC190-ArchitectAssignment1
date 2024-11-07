package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.HumanPlayer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Setup extends Application {
    static String userName;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Name Input");

        Label nameLabel = new Label("Welcome to our casino! What's your name?");

        TextField nameField = new TextField();
        nameField.setPromptText("Your Name");
        nameField.setPrefWidth(200);  // Set preferred width for the text field

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            userName = nameField.getText();
            HumanPlayer tempPlayer = HumanPlayer.getInstance();
            tempPlayer.setUsername(userName);
            primaryStage.close();

            Stage newWindow = new Stage();
            MainMenu.showWindow(newWindow);
        });

        // Layout for components
        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(nameLabel, nameField, submitButton);
        layout.setAlignment(Pos.CENTER);

        // Scene and Stage setup with smaller dimensions
        Scene scene = new Scene(layout, 400, 300);  // Reduced window size to 400x300
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}