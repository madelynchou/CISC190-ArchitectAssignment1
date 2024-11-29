package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.services.PlayerSavesService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SetupView extends Application {
    // TODO store pointer to Player and pass in instance of player when SetupView is constructed
    static String userName;
    PlayerSavesService playerSavesService;

    // TODO: create variable for BotService

    @Override
    public void start(Stage primaryStage) {
        // TODO: fire up BotService somewhere below

        // Check if player data file exists and load it
        if (playerSavesService.loadState()) {
            // Proceed directly to the MainMenu if data was loaded
            Stage mainMenuStage = new Stage();
            MainMenu.setupWindow(mainMenuStage);
            primaryStage.close();
        } else {
            // Show sign-in window if no data was loaded
            showSignInWindow(primaryStage);
        }
    }

    private void showSignInWindow(Stage primaryStage) {
        primaryStage.setTitle("Casino Royale - Sign In");

        // Welcome label with casino-style font
        Label welcomeLabel = new Label("Welcome to Casino Royale!");
        Label nameLabel = new Label("What's your name?");
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        nameLabel.setTextFill(Color.GOLD);

        // Text field with placeholder
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Your Name");
        nameField.setPrefWidth(250);
        welcomeLabel.setStyle(
                "-fx-background-color: #333333; " +
                        "-fx-text-fill: white; " +
                        "-fx-prompt-text-fill: #aaaaaa; " +
                        "-fx-background-radius: 10; " +
                        "-fx-padding: 10px;"
        );
        nameField.setStyle(
                "-fx-background-color: #333333; " +
                        "-fx-text-fill: white; " +
                        "-fx-prompt-text-fill: #aaaaaa; " +
                        "-fx-background-radius: 10; " +
                        "-fx-padding: 10px;"
        );


        // Submit button with casino-style hover effects
        Button submitButton = new Button("Enter the Casino");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        submitButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        );

        submitButton.setOnMouseEntered(e -> submitButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ff9900, #ff6600);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));
        submitButton.setOnMouseExited(e -> submitButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));

        // Submit button action
        submitButton.setOnAction(e -> {
            userName = nameField.getText();
            HumanPlayer tempPlayer = HumanPlayer.getInstance();
            tempPlayer.setUsername(userName);
            tempPlayer.setMoney(1000); // Default starting money if no file was loaded
            primaryStage.close();

            Stage newWindow = new Stage();
            MainMenu.setupWindow(newWindow);
        });

        // Layout setup
        VBox layout = new VBox(20); // Spacing between components
        layout.getChildren().addAll(welcomeLabel, nameLabel, nameField, submitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" + // Casino gradient
                        "-fx-padding: 20px;"
        );

        // Scene and Stage setup with smaller dimensions
        Scene scene = new Scene(layout, 350, 250); // Compact window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}