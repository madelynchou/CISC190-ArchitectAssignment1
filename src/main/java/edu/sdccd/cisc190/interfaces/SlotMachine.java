package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.machines.Slot;
import edu.sdccd.cisc190.machines.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SlotMachine extends Application {

    private static Label betAmount = new Label();
    private static Label slot1 = new Label("❓");
    private static Label slot2 = new Label("❓");
    private static Label slot3 = new Label("❓");
    private static Label won = new Label("Spin to see!");
    private static Label money = new Label("Balance: $" + HumanPlayer.getInstance().getMoney().toString());

    static Button spinButton = createStyledButton("Spin");
    static Button changeBet = createStyledButton("Change Bet");
    static Button mainMenu = createStyledButton("Return to Main Menu");

    static MainMenu.SlotOptions machineSelect;
    static Slot slotMachine;

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage, 0, MainMenu.SlotOptions.DIAMOND_DASH);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showWindow(Stage primaryStage, int betAmt, MainMenu.SlotOptions selectedMachine) {
        machineSelect = selectedMachine;
        switch (selectedMachine) {
            case DIAMOND_DASH -> slotMachine = new DiamondDash();
            case HONDA_TRUNK -> slotMachine = new HondaTrunk();
            case TREASURE_SPINS -> slotMachine = new TreasureSpins();
            case MEGA_MOOLAH -> slotMachine = new MegaMoolah();
            case RAINBOW_RICHES -> slotMachine = new RainbowRiches();
            default -> slotMachine = new DiamondDash();
        }

        primaryStage.setTitle("Casino Royale - Slot Machine");

        // Styled Labels
        betAmount.setText("You're betting: $" + betAmt);
        betAmount.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        betAmount.setTextFill(Color.LIGHTGOLDENRODYELLOW);

        slot1.setStyle("-fx-font-size: 60px;");
        slot2.setStyle("-fx-font-size: 60px;");
        slot3.setStyle("-fx-font-size: 60px;");
        slot1.setTextFill(Color.ORANGERED);
        slot2.setTextFill(Color.ORANGERED);
        slot3.setTextFill(Color.ORANGERED);

        won.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        won.setTextFill(Color.GOLD);

        money.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        money.setTextFill(Color.LIGHTGREEN);

        // Button Actions
        spinButton.setOnAction(e -> spin(betAmt, primaryStage));
        changeBet.setOnAction(e -> {
            primaryStage.close();
            Bet.showWindow(primaryStage, machineSelect);
        });
        mainMenu.setOnAction(e -> {
            primaryStage.close();
            MainMenu.setupWindow(primaryStage);
        });

        // Slots Display Row
        HBox slotsRow = new HBox(20, slot1, slot2, slot3);
        slotsRow.setAlignment(Pos.CENTER);

        // Main Layout
        VBox layout = new VBox(20, betAmount, won, money, slotsRow, spinButton, changeBet, mainMenu);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" +
                        "-fx-padding: 30px;"
        );

        // Scene Setup
        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void spin(int betAmt, Stage primaryStage) {
        if (HumanPlayer.getInstance().getMoney() < betAmt) {
            showAlert("You can't bet that much!", "Please try again with a lower bet.");
            primaryStage.close();
            Bet.showWindow(primaryStage, machineSelect);
        } else {
            slotMachine.initializeSymbols();
            String[] symbols = slotMachine.spin();
            slot1.setText(symbols[0]);
            slot2.setText(symbols[1]);
            slot3.setText(symbols[2]);

            int isWinner = DiamondDash.checkWinType(symbols);
            if (isWinner == 2 || isWinner == 3) {
                won.setText("Wow, you won!");
                HumanPlayer.getInstance().setMoney(slotMachine.checkIfWon(HumanPlayer.getInstance().getMoney(), symbols, betAmt));
            } else {
                won.setText("You lost :(");
                HumanPlayer.getInstance().setMoney(DiamondDash.checkIfWon(HumanPlayer.getInstance().getMoney(), symbols, betAmt));
            }

            money.setText("Balance: $" + HumanPlayer.getInstance().getMoney().toString());

            if (HumanPlayer.getInstance().getMoney() <= 0) {
                showAlert("Game over", "You're out of money! Better luck next time.");
                // Delete the file if it exists
                File file = new File("player_data.txt");
                if (file.exists()) {
                    if (!file.delete()) {
                        System.err.println("Failed to delete existing player_data.txt file.");
                        return;
                    }
                }

                primaryStage.close();
            }


        }
    }

    private static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10px 20px;"
        );

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