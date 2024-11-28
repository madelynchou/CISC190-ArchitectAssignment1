package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.services.PlayerSavesService;
import edu.sdccd.cisc190.services.SlotMachineManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

import java.util.Optional;

public class MainMenu extends Application {
    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupWindow(primaryStage);
    }

    static void setupWindow(Stage primaryStage) {
        primaryStage = primaryStage;
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

        // Add Delete File button
        Button deleteFileButton = createDeleteButton();
        layout.getChildren().add(deleteFileButton);

        // Setup and display the scene
        setupScene(primaryStage, layout);
    }

    private static Button createDeleteButton() {
        Button deleteButton = createSecondaryButton("Delete User File", "DON'T QUIT GAMBLING!!! 99.9% OF GAMBLERS QUIT FOR HITTING IT BIG!!!!!!");

        deleteButton.setOnAction(_ -> {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Are you sure?");
            confirmationAlert.setContentText("This will delete your user file. This action cannot be undone.");

            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("File Deletion");
                    successAlert.setHeaderText(null);
                    PlayerSavesService.deleteState();
                    successAlert.setContentText("Your file has been successfully deleted! (Logic not implemented)");
                    successAlert.showAndWait();
                    Platform.exit();
                } else {
                    Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
                    cancelAlert.setTitle("File Deletion Canceled");
                    cancelAlert.setHeaderText(null);
                    cancelAlert.setContentText("Your file has not been deleted.");
                    cancelAlert.showAndWait();
                }
            });
        });

        return deleteButton;
    }


    private static void handleDeleteFile() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete File");
        confirmationAlert.setHeaderText("Are you sure you want to delete your file?");
        confirmationAlert.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            showMessage("Your file has been deleted. (Logic not implemented)");
        } else {
            showMessage("File deletion canceled.");
        }
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

    private static Button createStyledButton(String text, String tooltipText) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        String defaultStyle = createButtonStyle("#ffcc00", "#ff9900", "black");
        String hoverStyle = createButtonStyle("#784800", "#943b00", "white");

        button.setStyle(defaultStyle);
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(_ -> button.setStyle(defaultStyle));

        if (tooltipText != null) {
            button.setTooltip(createTooltip(tooltipText));
        }

        return button;
    }

    private static Button createSecondaryButton(String text, String tooltipText) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        String defaultStyle = createButtonStyle("#cccccc", "#888888", "black");
        String hoverStyle = createButtonStyle("#aaaaaa", "#666666", "white");

        button.setStyle(defaultStyle);
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(_ -> button.setStyle(defaultStyle));

        if (tooltipText != null) {
            button.setTooltip(createTooltip(tooltipText));
        }

        return button;
    }
    private static String createButtonStyle(String topColor, String bottomColor, String textColor) {
        return "-fx-background-color: linear-gradient(to bottom, " + topColor + ", " + bottomColor + ");" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 20px;";
    }

    private static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void setupScene(Stage primaryStage, VBox layout) {
        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void addSlotOptionButtons(VBox layout, Stage primaryStage) {
        SlotOptions[] options = SlotOptions.values();
        for (int i = 0; i < options.length; i++) {
            SlotOptions option = options[i];
            Button slotButton;

            String tooltipText = switch (option) {
                case DIAMOND_DASH -> "Play Diamond Dash for sparkling wins! Min Bet: 15, Max Bet: 1000, Return: 2x";
                case HONDA_TRUNK -> "Spin the wheels with Honda Trunk. Min Bet: 5, Max Bet: 1000, Return: 1.5x";
                case MEGA_MOOLAH -> "Massive jackpots in Mega Moolah! Min Bet: 10, Max Bet: 1000, Return: 3x";
                case RAINBOW_RICHES -> "Discover treasures in Rainbow Riches. Min Bet: 25, Max Bet: 1000, Return: 5x";
                case TREASURE_SPINS -> "Uncover hidden wealth with Treasure Spins. Min Bet: 50, Max Bet: 1000, Return: 10x";
                case LEADERBOARD -> "View the current leaderboard standings.";
                case QUIT -> "Return to the Matrix";
            };

            if (i >= options.length - 2) { // Use secondary style for last buttons
                slotButton = createSecondaryButton(option.getDisplayOption(), tooltipText);
            } else {
                slotButton = createStyledButton(option.getDisplayOption(), tooltipText);
            }

            slotButton.setOnAction(_ -> handleSlotOption(primaryStage, option));
            layout.getChildren().add(slotButton);
        }
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
        // Stop all threads in SlotMachineManager
        SlotMachineManager.stopAllThreads();

        // Save the player's state
        PlayerSavesService.saveState();

        // Close the application
        primaryStage.close();

        // Show goodbye message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Goodbye!");
        alert.setContentText("Come back soon! 99.9% of gamblers quit before hitting it big!");
        alert.showAndWait();

        // Exit the program
        System.exit(0);
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

    private static Tooltip createTooltip(String text) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        tooltip.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-padding: 5px;");
        return tooltip;
    }
}