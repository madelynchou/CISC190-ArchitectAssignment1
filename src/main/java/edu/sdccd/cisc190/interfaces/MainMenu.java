package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.HumanPlayer;
import edu.sdccd.cisc190.Slot;
import edu.sdccd.cisc190.machines.DiamondDash;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage);
    }

    public static void showWindow(Stage primaryStage) {
        VBox layout = new VBox(20); // Increased spacing for casino feel
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" + // Casino gradient
                        "-fx-padding: 30px;" // Padding for spacing
        );
        layout.setAlignment(javafx.geometry.Pos.CENTER); // Center all elements

        primaryStage.setTitle("Casino Royale Menu");

        // Header Text
        Text header = new Text("Casino Royale");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        header.setFill(Color.GOLD);

        // User Info
        Text usernameLabel = new Text("Username: " + HumanPlayer.getInstance().getUsername());
        usernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        usernameLabel.setFill(Color.WHITE);

        Text moneyLabel = new Text("Money: $" + HumanPlayer.getInstance().getMoney());
        moneyLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        moneyLabel.setFill(Color.WHITE);

        // Add header and user info to layout
        layout.getChildren().addAll(header, usernameLabel, moneyLabel);

        // Create styled buttons
        for (SlotOptions option : SlotOptions.values()) {
            Button slotButton = createStyledButton(option.getDisplayOption());

            slotButton.setOnAction(e -> handleDisplayAction(primaryStage, option));

            layout.getChildren().add(slotButton);
        }

        // Scene and Stage setup
        Scene scene = new Scene(layout, 600, 600); // Larger size for casino feel
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void handleDisplayAction(Stage primaryStage, SlotOptions option) {
        switch (option) {
            case DIAMOND_DASH -> Bet.showWindow(primaryStage, option);
            case HONDA_TRUNK -> Bet.showWindow(primaryStage, option);
            case MEGA_MOOLAH -> Bet.showWindow(primaryStage, option);
            case RAINBOW_RICHES -> Bet.showWindow(primaryStage, option);
            case TREASURE_SPINS -> Bet.showWindow(primaryStage, option);
            case LEADERBOARD -> Leaderboard.showWindow(primaryStage);
            case QUIT -> {
                primaryStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Goodbye!");
                alert.setContentText("Come back soon! 99.9% of gamblers quit before hitting it big!");
                alert.showAndWait();
            }
            default -> displayMessage("Default option selected.");
        }
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

    private static void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Selection");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // SlotOptions enum
    public enum SlotOptions {
        DIAMOND_DASH("Diamond Dash"),
        HONDA_TRUNK("Honda Trunk"),
        MEGA_MOOLAH("Mega Moola"),
        RAINBOW_RICHES("Rainbow Riches"),
        TREASURE_SPINS("Treasure Spins"),
        LEADERBOARD("Leaderboard"),
        QUIT("Quit");

        private final String displayOption;

        SlotOptions(String displayOption) {
            this.displayOption = displayOption;
        }

        public String getDisplayOption() {
            return displayOption;
        }
    }
}