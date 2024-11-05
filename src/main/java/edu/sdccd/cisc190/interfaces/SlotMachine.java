package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.HumanPlayer;
import edu.sdccd.cisc190.Slot;
import edu.sdccd.cisc190.machines.DiamondDash;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        spinButton.setOnAction(e -> spin(betAmt));

        changeBet.setOnAction(e -> {
            primaryStage.close();
            Bet.showWindow(primaryStage);
        });

        mainMenu.setOnAction(e -> {
            primaryStage.close();
            MainMenu.showWindow(primaryStage);
        });

        // Layout for the slots and the button
        VBox layout = new VBox(20, betAmount, won, money, slot1, slot2, slot3, spinButton, changeBet, mainMenu);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private static void spin(int betAmt) {

        if (HumanPlayer.getInstance().getMoney() < betAmt || HumanPlayer.getInstance().getMoney() <= 0) {
            won.setText("Game over!");
            spinButton.setDisable(true);
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