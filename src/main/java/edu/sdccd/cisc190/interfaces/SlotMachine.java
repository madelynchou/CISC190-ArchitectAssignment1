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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SlotMachine extends Application {

    // Labels to display the emoji symbols and other information
    private static Label betAmount = new Label();
    private static Label slot1 = new Label("❓");
    private static Label slot2 = new Label("❓");
    private static Label slot3 = new Label("❓");
    private static Label won = new Label("Spin to see!");
    private static Label money = new Label("Balance: $" + HumanPlayer.getInstance().getMoney().toString());

    // Buttons with enhanced styling
    static Button spinButton = createStyledButton("Spin");
    static Button changeBet = createStyledButton("Change Bet");
    static Button mainMenu = createStyledButton("Return to Main Menu");

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage, 0); // Replace 0 with actual betAmt if needed
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showWindow(Stage primaryStage, int betAmt) {
        primaryStage.setTitle("Slot Machine");

        // Set bet amount label text
        betAmount.setText("You're betting: $" + betAmt);
        betAmount.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        betAmount.setTextFill(Color.LIGHTGOLDENRODYELLOW);

        // Increase font size for slot labels to make them appear larger
        slot1.setStyle("-fx-font-size: 60px;");
        slot2.setStyle("-fx-font-size: 60px;");
        slot3.setStyle("-fx-font-size: 60px;");
        slot1.setTextFill(Color.ORANGERED);
        slot2.setTextFill(Color.ORANGERED);
        slot3.setTextFill(Color.ORANGERED);

        // Win/Loss label styling
        won.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        won.setTextFill(Color.GOLD);

        // Money label styling
        money.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        money.setTextFill(Color.LIGHTGREEN);

        // Button actions
        spinButton.setOnAction(e -> spin(betAmt, primaryStage));
        changeBet.setOnAction(e -> {
            primaryStage.close();
            Bet.showWindow(primaryStage);
        });
        mainMenu.setOnAction(e -> {
            primaryStage.close();
            MainMenu.showWindow(primaryStage);
        });

        // Slots display row
        HBox slotsRow = new HBox(20, slot1, slot2, slot3);
        slotsRow.setAlignment(Pos.CENTER);

        // Main layout
        VBox layout = new VBox(20, betAmount, won, money, slotsRow, spinButton, changeBet, mainMenu);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: darkslateblue; -fx-padding: 30px;");

        // Scene setup
        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void spin(int betAmt, Stage primaryStage) {
        if (HumanPlayer.getInstance().getMoney() < betAmt) {
            showAlert("You can't bet that much!", "Please try again with a lower bet.");
            primaryStage.close();
            Bet.showWindow(primaryStage);
        } else {
            // Spin the slot machine and update symbols
            String[] symbols = DiamondDash.spin();
            slot1.setText(symbols[0]);
            slot2.setText(symbols[1]);
            slot3.setText(symbols[2]);

            // Determine and display the result
            boolean isWinner = DiamondDash.isWinner(symbols);
            if (isWinner) {
                won.setText("Wow, you won!");
                HumanPlayer.getInstance().setMoney(HumanPlayer.getInstance().getMoney() + betAmt * DiamondDash.returnAmt);
            } else {
                won.setText("You lost :(");
                HumanPlayer.getInstance().setMoney(HumanPlayer.getInstance().getMoney() - betAmt);
            }

            // Update money label
            money.setText("Balance: $" + HumanPlayer.getInstance().getMoney().toString());

            // Check if player is out of money
            if (HumanPlayer.getInstance().getMoney() <= 0) {
                showAlert("Game over", "You're out of money! Better luck next time.");
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

        // Hover effect
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