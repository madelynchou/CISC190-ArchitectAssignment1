package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.services.BotService;
import edu.sdccd.cisc190.services.PlayerSavesService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
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

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

/**
 * MainMenuView is the main menu screen of the Casino Royale application.
 * It provides navigation to various sections of the game, including
 * slot machine options, a leaderboard, and motivational resources.
 * The class also handles pausing bots and managing user data.
 */
public class MainMenuView extends Application {
    /**
     * A static list of motivational URLs to be shown to users.
     */
    private static final ArrayList<String> MOTIVATIONAL_URLS = new ArrayList<>() {{
        add("https://www.instagram.com/reel/C_JDcZVya_1/?igsh=NTc4MTIwNjQ2YQ=="); // Add your own motivational URLs
        add("https://www.instagram.com/reel/DAZR6WlSsVk/?igsh=NTc4MTIwNjQ2YQ==");
        add("https://www.instagram.com/reel/DCz7-k5JxLT/?igsh=NTc4MTIwNjQ2YQ==");
        add("https://www.instagram.com/reel/DB1tqWqNWL8/?igsh=NTc4MTIwNjQ2YQ==");
        add("https://www.instagram.com/reel/DB9nUPfS1WC/?igsh=NTc4MTIwNjQ2YQ==");
        add("https://www.instagram.com/reel/DBpDgUVoFcK/?igsh=NTc4MTIwNjQ2YQ==");
        add("https://www.instagram.com/reel/DB8nzu7oW8K/?igsh=NTc4MTIwNjQ2YQ==");
        add("https://www.instagram.com/reel/C7ZnLuWoRbW/?igsh=NTc4MTIwNjQ2YQ==");
        add("https://www.instagram.com/reel/C_8R_SJPOe6/?igsh=NTc4MTIwNjQ2YQ==");
    }};

    /**
     * The primary stage of the application.
     */
    static Stage primaryStage;


    /**
     * Entry point for the JavaFX application.
     *
     * @param primaryStage the primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        MainMenuView.primaryStage = primaryStage;
        setupWindow(primaryStage);
    }

    /**
     * Configures and displays the main menu interface.
     *
     * @param primaryStage the primary stage for the application.
     */
    static void setupWindow(Stage primaryStage) {
        VBox layout = createMainLayout();
        primaryStage.setTitle("Casino Royale Menu");

        // Add header and user info
        layout.getChildren().addAll(
                createHeader(),
                createUserInfo("Username: %s".formatted(HumanPlayer.getInstance().getName())),
                createUserInfo("Money: $%d".formatted(HumanPlayer.getInstance().getMoney()))
        );

        // Add slot option buttons
        addSlotOptionButtons(layout, primaryStage);

        Button motivationButton = createMotivationButton();
        layout.getChildren().add(motivationButton);

        Button pauseButton = createPauseButton();
        layout.getChildren().add(pauseButton);

        // Add Delete File button
        Button deleteFileButton = createDeleteButton();
        layout.getChildren().add(deleteFileButton);

        // Setup and display the scene
        setupScene(primaryStage, layout);
    }

    /**
     * Creates a button that opens a random motivational URL in the browser.
     *
     * @return the motivation button.
     */
    private static Button createMotivationButton() {
        Button motivationButton = createSecondaryButton("Motivation", "Get inspired to keep going!");

        motivationButton.setOnAction(_ -> {
            Random random = new Random();
            int randomIndex = random.nextInt(MOTIVATIONAL_URLS.size());
            String selectedUrl = MOTIVATIONAL_URLS.get(randomIndex);

            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(selectedUrl));
            } catch (IOException | URISyntaxException e) {
                showMessage("Failed to open the link. Please try again.");
            }
        });

        return motivationButton;
    }

    /**
     * Creates a button to pause or unpause the bots in the game.
     *
     * @return the pause/unpause button.
     */
    private static Button createPauseButton() {
        Button pauseButton = createSecondaryButton("Pause", "Stop all of the bots from playing");

        // Create a binding to dynamically set the button text
        StringBinding pauseButtonTextBinding = new StringBinding() {
            {
                super.bind(BotService.pauseFlagProperty());
            }

            @Override
            protected String computeValue() {
                return BotService.pauseFlagProperty().get() ? "Unpause" : "Pause";
            }
        };

        // Bind the button's text property to the binding
        pauseButton.textProperty().bind(pauseButtonTextBinding);

        pauseButton.setTooltip(createTooltip("Pause all of the bots from playing"));

        pauseButton.setOnAction(_ -> {
            if (BotService.pauseFlagProperty().get()) {
                BotService.unpause();
                showMessage("Bots have been unpaused and are now spinning");
            } else {
                BotService.pause();
                showMessage("All bots have been paused");
            }
        });

        return pauseButton;
    }

    /**
     * Creates a button to delete the user's save file with confirmation alerts.
     *
     * @return the delete user file button.
     */
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
                    quitApplication();

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

    /**
     * Creates and configures the main layout for the menu.
     * The layout is styled with padding and a gradient background.
     *
     * @return a VBox containing the main layout.
     */
    private static VBox createMainLayout() {
        VBox layout = new VBox(20);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" +
                        "-fx-padding: 30px;"
        );
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        return layout;
    }

    /**
     * Creates a header text for the main menu.
     * The header displays the title "Casino Royale" with bold styling.
     *
     * @return a Text object representing the header.
     */
    private static Text createHeader() {
        Text header = new Text("Casino Royale");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        header.setFill(Color.GOLD);
        return header;
    }

    /**
     * Creates a styled text element to display user information.
     * The text is styled with a semi-bold font and white color.
     *
     * @param text the information to display.
     * @return a Text object representing the user information.
     */
    private static Text createUserInfo(String text) {
        Text userInfo = new Text(text);
        userInfo.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        userInfo.setFill(Color.WHITE);
        return userInfo;
    }

    /**
     * Creates a styled button with hover effects and an optional tooltip.
     * The button changes styles on hover for a better user experience.
     *
     * @param text        the text displayed on the button.
     * @param tooltipText the tooltip text for the button, or null if none.
     * @return a styled Button object.
     */
    private static Button createStyledButton(String text, String tooltipText) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        String defaultStyle = createButtonStyle("#ffcc00", "#ff9900", "black");
        String hoverStyle = createButtonStyle("#784800", "#943b00", "white");

        return getButton(tooltipText, button, defaultStyle, hoverStyle);
    }

    /**
     * Creates a secondary styled button with hover effects and an optional tooltip.
     * The button has a lighter theme and is intended for less prominent actions.
     *
     * @param text        the text displayed on the button.
     * @param tooltipText the tooltip text for the button, or null if none.
     * @return a secondary styled Button object.
     */
    private static Button createSecondaryButton(String text, String tooltipText) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        String defaultStyle = createButtonStyle("#cccccc", "#888888", "black");
        String hoverStyle = createButtonStyle("#aaaaaa", "#666666", "white");

        return getButton(tooltipText, button, defaultStyle, hoverStyle);
    }

    private static Button getButton(String tooltipText, Button button, String defaultStyle, String hoverStyle) {
        button.setStyle(defaultStyle);
        button.setOnMouseEntered(_ -> button.setStyle(hoverStyle));
        button.setOnMouseExited(_ -> button.setStyle(defaultStyle));

        if (tooltipText != null) {
            button.setTooltip(createTooltip(tooltipText));
        }

        return button;
    }

    /**
     * Creates a CSS style string for a button.
     *
     * @param topColor    the top gradient color.
     * @param bottomColor the bottom gradient color.
     * @param textColor   the text color for the button.
     * @return a string representing the CSS style for the button.
     */
    private static String createButtonStyle(String topColor, String bottomColor, String textColor) {
        return "-fx-background-color: linear-gradient(to bottom, %s, %s);-fx-text-fill: %s;-fx-background-radius: 10;-fx-padding: 10px 20px;".formatted(topColor, bottomColor, textColor);
    }

    /**
     * Displays an informational message in an alert dialog.
     *
     * @param message the message to display.
     */
    private static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Configures and displays the main scene of the application.
     *
     * @param primaryStage the primary stage for the application.
     * @param layout       the layout to be displayed in the scene.
     */
    private static void setupScene(Stage primaryStage, VBox layout) {
        // Adjust the width and height as desired
        Scene scene = new Scene(layout, 800, 800); // Changed from 600, 600 to 800, 800
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Adds buttons for slot machine options to the provided layout.
     * The buttons allow navigation to various game features.
     *
     * @param layout       the layout to which buttons are added.
     * @param primaryStage the primary stage for the application.
     */
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

    /**
     * Handles actions for slot machine options.
     *
     * @param primaryStage the primary stage for the application.
     * @param option       the selected slot option.
     */
    private static void handleSlotOption(Stage primaryStage, SlotOptions option) {
        switch (option) {
            case DIAMOND_DASH, HONDA_TRUNK, MEGA_MOOLAH, RAINBOW_RICHES, TREASURE_SPINS ->
                    BetView.showWindow(primaryStage, option);
            case LEADERBOARD -> LeaderboardView.showWindow(primaryStage);
            case QUIT -> quitApplication();
            default -> showMessage("Default option selected.");
        }
    }

    /**
     * Exits the application with a goodbye message.
     */
    private static void quitApplication() {
        // Show goodbye message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Goodbye!");
        alert.setContentText("Come back soon! 99.9% of gamblers quit before hitting it big!");
        alert.showAndWait();

        Platform.exit();

        // Exit the program
        System.exit(0);
    }

    /**
     * Enum representing the slot machine options available in the game.
     */
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

        /**
         * Gets the display name for the slot option.
         *
         * @return the display name.
         */
        public String getDisplayOption() {
            return displayOption;
        }
    }

    /**
     * Creates a tooltip with styled text.
     *
     * @param text the text for the tooltip.
     * @return the styled tooltip.
     */
    private static Tooltip createTooltip(String text) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        tooltip.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-padding: 5px;");
        return tooltip;
    }
}