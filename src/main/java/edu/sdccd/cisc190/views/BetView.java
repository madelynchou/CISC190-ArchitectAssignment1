package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.machines.*;
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

import static edu.sdccd.cisc190.views.SlotMachineView.slotMachine;

public class BetView extends Application {
    static int betAmt;
    private static final Label maxBet = new Label();
    private static final Label minBet = new Label();
    private static final Label returnAmount = new Label();

    @Override
    public void start(Stage primaryStage) {
        // Placeholder for launching the window
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showWindow(Stage primaryStage, MainMenuView.SlotOptions selectedMachine) {
        primaryStage.setTitle("Casino Royale - Place Your Bet");

        MainMenuView.SlotOptions machineSelect = selectedMachine;
        switch (selectedMachine) {
            case DIAMOND_DASH -> slotMachine = new DiamondDash();
            case HONDA_TRUNK -> slotMachine = new HondaTrunk();
            case TREASURE_SPINS -> slotMachine = new TreasureSpins();
            case MEGA_MOOLAH -> slotMachine = new MegaMoolah();
            case RAINBOW_RICHES -> slotMachine = new RainbowRiches();
            default -> slotMachine = new DiamondDash();
        }

        // Styled label
        Label nameLabel = new Label("How much do you want to bet?");
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        nameLabel.setTextFill(Color.GOLD);

        maxBet.setText("Max. Bet: " + slotMachine.getMaxBet());
        maxBet.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 15));
        minBet.setText("Min. Bet: " + slotMachine.getMinBet());
        minBet.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 15));
        returnAmount.setText("Return: " + slotMachine.getReturnAmt());
        returnAmount.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 15));
        maxBet.setTextFill(Color.RED);
        minBet.setTextFill(Color.RED);
        returnAmount.setTextFill(Color.RED);


        // Styled text field
        TextField numericTextField = new TextField();
        numericTextField.setPromptText("Enter numbers only");
        numericTextField.setPrefWidth(250); // Set width for better alignment
        numericTextField.setStyle(
                "-fx-background-color: #333333; " +
                        "-fx-text-fill: white; " +
                        "-fx-prompt-text-fill: #aaaaaa; " +
                        "-fx-background-radius: 10; " +
                        "-fx-padding: 10px;"
        );

        // Restrict input to digits only
        numericTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // \\d* matches zero or more digits
                numericTextField.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-numeric characters
            }
        });

        // Styled Main Menu button
        Button mainMenu = createStyledButton("Main Menu");
        mainMenu.setOnAction(e -> {
            MainMenuView.setupWindow(primaryStage);
        });

        // Styled submit button
        Button submitButton = createStyledButton("Place Bet");

        submitButton.setOnAction(e -> {
            if (!numericTextField.getText().isEmpty()) {
                betAmt = Integer.parseInt(numericTextField.getText());
                primaryStage.close();

                Stage newWindow = new Stage();
                SlotMachineView.showWindow(newWindow, betAmt, selectedMachine);
            }
        });

        // Slot information
        HBox slotInformation = new HBox(10, maxBet, minBet, returnAmount);
        slotInformation.setAlignment(Pos.CENTER);

        // Layout setup
        VBox layout = new VBox(20); // Increased spacing for visual clarity
        layout.getChildren().addAll(nameLabel, slotInformation, numericTextField, submitButton, mainMenu);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" +
                        "-fx-padding: 30px;"
        );


        // Scene setup
        Scene scene = new Scene(layout, 400, 300); // Smaller and compact
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Styled button method for consistency
    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        );

        // Hover effects
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ff9900, #ff6600);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        ));

        return button;
    }
}