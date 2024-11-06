package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.HumanPlayer;
import edu.sdccd.cisc190.Slot;
import edu.sdccd.cisc190.machines.DiamondDash;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SlotMachine extends Application {

    // Labels to display the emoji symbols
    private static Label betAmount = new Label();
    private static Label slot1 = new Label("❓");
    private static Label slot2 = new Label("❓");
    private static Label slot3 = new Label("❓");
    static Button spinButton = new Button("Spin");

    private static Label won = new Label("Spin to see!");
    private static Label money = new Label(HumanPlayer.getInstance().getMoney().toString());

    static Button changeBet = new Button("Change Bet");
    static Button mainMenu = new Button("Return to Main Menu");

    @Override
    public void start(Stage primaryStage) {
    }

    // Method to spin the slot machine and update the labels with new symbols

    public static void main(String[] args) {
        launch(args);
    }

    public static void showWindow(Stage primaryStage, int betAmt) {
        primaryStage.setTitle("Slot Machine");

        betAmount.setText("" + betAmt);

        // "Spin" button to spin the slot machine
        spinButton.setOnAction(e -> spin(betAmt, primaryStage));

        changeBet.setOnAction(e -> {
            primaryStage.close();
            Bet.showWindow(primaryStage);
        });

        mainMenu.setOnAction(e -> {
            primaryStage.close();
            MainMenu.showWindow(primaryStage);
        });

        // Create an HBox for the slot labels to align them horizontally
        HBox slotsRow = new HBox(10, slot1, slot2, slot3);
        slotsRow.setAlignment(Pos.CENTER);

        // Main layout for the entire interface
        VBox layout = new VBox(20, betAmount, won, money, slotsRow, spinButton, changeBet, mainMenu);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void spin(int betAmt, Stage primaryStage) {
        if (HumanPlayer.getInstance().getMoney() < betAmt) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection");
            alert.setHeaderText(null);
            alert.setContentText("You can't bet that much!");
            alert.showAndWait();

            primaryStage.close();
            Bet.showWindow(primaryStage);
        } else {
            // Call SlotMachine class to get random symbols
            String[] symbols = DiamondDash.spin();

            // Update the slot labels with the new symbols
            slot1.setText(symbols[0]);
            slot2.setText(symbols[1]);
            slot3.setText(symbols[2]);

            boolean isWinner = DiamondDash.isWinner(symbols);
            if (isWinner) {
                won.setText("Wow you won!");
                HumanPlayer.getInstance().setMoney(HumanPlayer.getInstance().getMoney() + betAmt * DiamondDash.returnAmt);
                money.setText(HumanPlayer.getInstance().getMoney().toString());
            } else {
                won.setText("You lost :(");
                HumanPlayer.getInstance().setMoney(HumanPlayer.getInstance().getMoney() - betAmt);
                money.setText(HumanPlayer.getInstance().getMoney().toString());
            }
        }
    }
}