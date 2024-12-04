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

/**
 * The SetupView class is the first screen of the Casino Royale application.
 * It prompts the user to enter their name and serves as the gateway to the Main Menu.
 * If player data already exists, the application skips this screen and proceeds to the Main Menu.
 */
public class SetupView extends Application {
    /**
     * The username entered by the user. This is used to identify the player in the game.
     */
    static String userName;

    /**
     * The entry point for the JavaFX application. Determines whether to load existing player data
     * or show the sign-in window for new players.
     *
     * @param primaryStage the primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Check if player data file exists and load it
        if (PlayerSavesService.loadState()) {
            // Proceed directly to the MainMenu if data was loaded
            Stage mainMenuStage = new Stage();
            MainMenuView.setupWindow(mainMenuStage);
            primaryStage.close();
        } else {
            // Show sign-in window if no data was loaded
            showSignInWindow(primaryStage);
        }
    }

    /**
     * Displays the sign-in window for the user to enter their name.
     * @param primaryStage the primary stage for the sign-in window.
     */
    private void showSignInWindow(Stage primaryStage) {
        primaryStage.setTitle("Casino - Sign In");

        // Create labels, text field, and button for the sign-in window
        Label welcomeLabel = new Label("Welcome to the Casino!");
        Label nameLabel = new Label("What's your name?");
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        nameLabel.setTextFill(Color.GOLD);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Your Name");
        nameField.setPrefWidth(250);

        Button submitButton = new Button("Enter the Casino");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Configure button styles and hover effects
        submitButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        );

        submitButton.setOnMouseEntered(_ -> submitButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ff9900, #ff6600);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));

        submitButton.setOnMouseExited(_ -> submitButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));

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

        // Define action for the submit button
        submitButton.setOnAction(_ -> {
            userName = nameField.getText();
            HumanPlayer tempPlayer = HumanPlayer.getInstance();
            tempPlayer.setUsername(userName);
            tempPlayer.setMoney(1000); // Default starting money if no file was loaded
            primaryStage.close();

            Stage newWindow = new Stage();
            MainMenuView.setupWindow(newWindow);
        });

        // Layout and styling for the sign-in window
        VBox layout = new VBox(20); // Spacing between components
        layout.getChildren().addAll(welcomeLabel, nameLabel, nameField, submitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" + // Casino gradient
                        "-fx-padding: 20px;"
        );

        // Create and show the scene
        Scene scene = new Scene(layout, 350, 250); // Compact window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args the command-line arguments (if any).
     */
    public static void main(String[] args) {
        launch(args);
    }
}