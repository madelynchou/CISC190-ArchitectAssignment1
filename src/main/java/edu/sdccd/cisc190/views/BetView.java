package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.machines.*;
import edu.sdccd.cisc190.players.bots.Bot;
import edu.sdccd.cisc190.services.SlotMachineManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Platform;

import static edu.sdccd.cisc190.views.SlotMachineView.slotMachine;

/**
 * The BetView class represents a JavaFX view for users to place their bets on a selected slot machine.
 * It allows users to enter a bet amount, displays slot machine limits and return information,
 * and navigates to the SlotMachineView or MainMenuView.
 */
public class BetView extends Application {
    /**
     * The amount the user chooses to bet.
     */
    static int betAmt;

    // Labels for displaying slot machine betting limits and return amounts
    private static final Label maxBet = new Label();
    private static final Label minBet = new Label();
    private static final Label returnAmount = new Label();

    /**
     * The entry point for JavaFX applications.
     * This method is overridden but not used directly in this class.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Placeholder for launching the window
    }

    /**
     * Main method for launching the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Displays the betting window where users can place a bet for a selected slot machine.
     *
     * @param primaryStage    The primary stage for the application.
     * @param selectedMachine The selected slot machine type.
     */
    public static void showWindow(Stage primaryStage, MainMenuView.SlotOptions selectedMachine) {
        primaryStage.setTitle("Casino - Place Your Bet");

        // Set the onCloseRequest handler to quit the application when the window is closed
        primaryStage.setOnCloseRequest(_ -> {
            SlotMachineManager.stopAllThreads();
            Platform.exit();
        });

        // Initialize the selected slot machine based on user choice
        switch (selectedMachine) {
            case HONDA_TRUNK -> slotMachine = new HondaTrunk();
            case TREASURE_SPINS -> slotMachine = new TreasureSpins();
            case MEGA_MOOLAH -> slotMachine = new MegaMoolah();
            case RAINBOW_RICHES -> slotMachine = new RainbowRiches();
            default -> slotMachine = new DiamondDash();
        }

        // Create a styled label prompting the user for their bet amount
        Label nameLabel = new Label("How much do you want to bet?");
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        nameLabel.setTextFill(Color.GOLD);

        // Set up labels to display slot machine limits and expected return
        SlotMachineView.infoSetText(maxBet, minBet, returnAmount);

        // Create a text field for the user to enter their bet amount
        TextField numericTextField = new TextField();
        numericTextField.setPromptText("Enter numbers only");
        numericTextField.setPrefWidth(250);
        numericTextField.setStyle(
                "-fx-background-color: #333333; " +
                        "-fx-text-fill: white; " +
                        "-fx-prompt-text-fill: #aaaaaa; " +
                        "-fx-background-radius: 10; " +
                        "-fx-padding: 10px;"
        );

        // Restrict the input to numeric values only
        numericTextField.textProperty().addListener((_, _, newValue) -> {
            if (!newValue.matches("\\d*")) { // Allow only digits
                numericTextField.setText(newValue.replaceAll("\\D", "")); // Remove non-numeric characters
            }
        });

        // Create the Main Menu button and attach an action to return to the MainMenuView
        Button mainMenu = createStyledButton("Main Menu");
        mainMenu.setOnAction(_ -> MainMenuView.setupWindow(primaryStage));

        // Create the Place Bet button to submit the user's bet
        Button submitButton = createStyledButton("Place Bet");
        submitButton.setOnAction(_ -> {
            if (!numericTextField.getText().isEmpty()) {
                betAmt = Integer.parseInt(numericTextField.getText()); // Get the bet amount
                primaryStage.close();

                // Open the SlotMachineView with the bet amount and selected slot machine
                Stage newWindow = new Stage();
                SlotMachineView.showWindow(newWindow, betAmt, selectedMachine);
            }
        });

        // Create a horizontal box to display slot machine information (max/min bet and return amount)
        HBox slotInformation = new HBox(10, maxBet, minBet, returnAmount);
        slotInformation.setAlignment(Pos.CENTER);

        // Arrange all elements in a vertical layout
        VBox layout = new VBox(20); // Increased spacing for better visuals
        layout.getChildren().addAll(nameLabel, slotInformation, numericTextField, submitButton, mainMenu);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" +
                        "-fx-padding: 30px;"
        );

        // Set up the scene and display it on the primary stage
        Scene scene = new Scene(layout, 400, 300); // Compact layout size
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Creates a styled button with hover effects.
     *
     * @param text The text to display on the button.
     * @return A styled Button object.
     */
    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        );

        // Add hover effects for better user interaction
        button.setOnMouseEntered(_ -> button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ff9900, #ff6600);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));
        button.setOnMouseExited(_ -> button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));

        return button;
    }
}