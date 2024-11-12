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

    public static final String APP_NAME_FILE = "AppName.txt";

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage);
    }

    public static void showWindow(Stage primaryStage) {
        VBox layout = new VBox(10); // spacing between buttons
        primaryStage.setTitle("Menu Options");

        // Creating menu buttons
        Text usernameLabel = new Text(10, 50, "Username: " + HumanPlayer.getInstance().getUsername());
        Text moneyLabel = new Text(10, 50, "Money: " + HumanPlayer.getInstance().getMoney().toString());

        for(SlotOptions option: SlotOptions.values()) {
            Button slotButton = new Button(option.getDisplayOption());

            slotButton.setOnAction(e -> handleDisplayAction(primaryStage, option));

            layout.getChildren().add(slotButton);
        }

        // Scene and Stage setup
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static void handleDisplayAction(Stage primaryStage, SlotOptions option) {
        switch (option) {
            case DIAMOND_DASH -> {
                primaryStage.close();
                Stage newWindow = new Stage();
                Bet.showWindow(newWindow);
            }
            case HONDA_TRUNK -> displayMessage("Honda Trunk");
            case MEGA_MOOLAH -> displayMessage("mega moola");
            case RAINBOW_RICHES -> displayMessage("rainbow");
            case TREASURE_SPINS -> displayMessage("treasure");
            case QUIT -> {
                primaryStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cya");
                alert.setContentText("Come back soon! 99.9% of gamblers quit before hitting it big!");
                alert.showAndWait();
            }
            default -> displayMessage("default option");
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

    //declare enum for slots down here
    public enum SlotOptions {
        DIAMOND_DASH("Diamond Dash"),
        HONDA_TRUNK("Honda Trunk"),
        MEGA_MOOLAH("Mega Moola"),
        RAINBOW_RICHES("Rainbow Riches"),
        TREASURE_SPINS("Treasure Spins"),
        QUIT("Quit");

        private final String displayOption;

        SlotOptions(String displayOption) {
            this.displayOption = displayOption;
        }

        public String getDisplayOption() {
            return displayOption;
        }
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
}