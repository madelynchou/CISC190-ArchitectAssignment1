package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        setupWindow(primaryStage);
    }

    static void setupWindow(Stage primaryStage) {
        VBox layout = createMainLayout();
        primaryStage.setTitle("Casino Royale Menu");

        // Add header and user info
        layout.getChildren().addAll(
                createHeader(),
                createUserInfo("Username: " + HumanPlayer.getInstance().getUsername(), Color.WHITE),
                createUserInfo("Money: $" + HumanPlayer.getInstance().getMoney(), Color.WHITE)
        );

        // Add slot option buttons
        addSlotOptionButtons(layout, primaryStage);

        // Setup and display the scene
        setupScene(primaryStage, layout);
    }

    private static VBox createMainLayout() {
        VBox layout = new VBox(20);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" +
                        "-fx-padding: 30px;"
        );
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        return layout;
    }

    private static Text createHeader() {
        Text header = new Text("Casino Royale");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        header.setFill(Color.GOLD);
        return header;
    }

    private static Text createUserInfo(String text, Color color) {
        Text userInfo = new Text(text);
        userInfo.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        userInfo.setFill(color);
        return userInfo;
    }

    private static void addSlotOptionButtons(VBox layout, Stage primaryStage) {
        SlotOptions[] options = SlotOptions.values();

        for (int i = 0; i < options.length; i++) {
            Button button;
            if (i < 5) {
                // First 4 options use slotButton style
                button = createStyledButton(options[i].getDisplayOption());
            } else {
                // Last 2 options use secondaryButton style
                button = createSecondaryStyledButton(options[i].getDisplayOption());
            }
            int index = i; // For lambda capture
            button.setOnAction(e -> handleSlotOption(primaryStage, options[index]));
            layout.getChildren().add(button);
        }
    }

    private static void setupScene(Stage primaryStage, VBox layout) {
        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle(createButtonStyle("#ffcc00", "#ff9900", "black"));

        button.setOnMouseEntered(e -> button.setStyle(createButtonStyle("#784800", "#943b00", "white")));
        button.setOnMouseExited(e -> button.setStyle(createButtonStyle("#784800", "#943b00", "black")));

        return button;
    }


    private static String createButtonStyle(String topColor, String bottomColor, String textColor) {
        return "-fx-background-color: linear-gradient(to bottom, " + topColor + ", " + bottomColor + ");" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 20px;";
    }


    private static Button createSecondaryStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        button.setStyle(createSecondaryButtonStyle("#666666", "#333333", "white"));

        button.setOnMouseEntered(e -> button.setStyle(createSecondaryButtonStyle("#555555", "#222222", "#ffcc00")));
        button.setOnMouseExited(e -> button.setStyle(createSecondaryButtonStyle("#666666", "#333333", "white")));

        return button;
    }

    private static String createSecondaryButtonStyle(String topColor, String bottomColor, String textColor) {
        return "-fx-background-color: linear-gradient(to bottom, " + topColor + ", " + bottomColor + ");" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 20px;";
    }


    private static void handleSlotOption(Stage primaryStage, SlotOptions option) {
        switch (option) {
            case DIAMOND_DASH, HONDA_TRUNK, MEGA_MOOLAH, RAINBOW_RICHES, TREASURE_SPINS ->
                    BetView.showWindow(primaryStage, option);
            case LEADERBOARD -> LeaderboardView.showWindow(primaryStage);
            case QUIT -> quitApplication(primaryStage);
            default -> showMessage("Default option selected.");
        }
    }

    private static void quitApplication(Stage primaryStage) {
        savePlayerData();
        primaryStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Goodbye!");
        alert.setContentText("Come back soon! 99.9% of gamblers quit before hitting it big!");
        alert.showAndWait();
    }

    private static void savePlayerData() {
        HumanPlayer player = HumanPlayer.getInstance();
        String data = "Username: " + player.getUsername() + ", Money: $" + player.getMoney();

        try {
            // Delete the file if it exists
            File file = new File("player_data.txt");
            if (file.exists()) {
                if (!file.delete()) {
                    System.err.println("Failed to delete existing player_data.txt file.");
                    return;
                }
            }

            // Write new data to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(data);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }
    private static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Selection");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public enum SlotOptions {
        DIAMOND_DASH("Diamond Dash"),
        HONDA_TRUNK("Honda Trunk"),
        MEGA_MOOLAH("Mega Moolah"),
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