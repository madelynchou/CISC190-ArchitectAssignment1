package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.machines.Slot;
import edu.sdccd.cisc190.machines.*;
import edu.sdccd.cisc190.services.PlayerSavesService;
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

public class SlotMachineView extends Application {

    private static final Label betAmount = new Label();
    private static final Label slot1 = new Label("❓");
    private static final Label slot2 = new Label("❓");
    private static final Label slot3 = new Label("❓");
    private static final Label won = new Label("Spin to see!");
    private static final Label money = new Label("Balance: $" + HumanPlayer.getInstance().getMoney().toString());

    static Button spinButton = createStyledButton("Spin");
    static Button changeBet = createStyledButton("Change Bet");
    static Button mainMenu = createStyledButton("Return to Main Menu");

    static MainMenu.SlotOptions machineSelect;
    static Slot slotMachine;
    static PlayerSavesService playerSavesService;

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

        System.out.println("Min Bet: " + Slot.minBet);
        System.out.println("Min Bet: " + Slot.returnAmt);
        System.out.println("Max Bet: " + Slot.maxBet);
        System.out.println("Max Bet: " + Slot.symbols);


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
            BetView.showWindow(primaryStage, machineSelect);
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
            showAlert("You can't bet that much!", "You don't have that much money. Please try again with a lower bet.");
            primaryStage.close();
            BetView.showWindow(primaryStage, machineSelect);
            System.out.println(slotMachine.getMaxBet());
        } else if (betAmt > slotMachine.getMaxBet()) {
            showAlert("You can't bet that much!", "You've exceeded the maximum betting limit for this machine. Please try again with a lower bet.");
            primaryStage.close();
            BetView.showWindow(primaryStage, machineSelect);
            System.out.println(slotMachine.getMaxBet());
        } else if (betAmt < slotMachine.getMinBet()) {
            showAlert("You can't bet that much!", "You're below the minimum betting limit for this machine. Please try again with a higher bet.");
            primaryStage.close();
            BetView.showWindow(primaryStage, machineSelect);
            System.out.println(slotMachine.getMinBet());
        } else {
            slotMachine.initializeSymbols();
            String[] symbols = Slot.generateSpunSymbols();
            slot1.setText(symbols[0]);
            slot2.setText(symbols[1]);
            slot3.setText(symbols[2]);

            int isWinner = DiamondDash.evaluateWinCondition(symbols);
            if (isWinner == 2 || isWinner == 3) {
                won.setText("Wow, you won!");
                HumanPlayer.getInstance().setMoney(Slot.calculatePayout(HumanPlayer.getInstance().getMoney(), symbols, betAmt));
            } else {
                won.setText("You lost :(");
                HumanPlayer.getInstance().setMoney(DiamondDash.calculatePayout(HumanPlayer.getInstance().getMoney(), symbols, betAmt));
            }

            money.setText("Balance: $" + HumanPlayer.getInstance().getMoney().toString());

            if (HumanPlayer.getInstance().getMoney() <= 0) {
                showAlert("Game over", "You're out of money! Better luck next time.");
                // Delete the file if it exists
                PlayerSavesService.deleteState();
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