package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.services.PlayerSavesService;
import javafx.application.Application;
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

import java.util.Optional;

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

        // Add Delete File button
        Button deleteFileButton = createStyledButton("Delete File");
        deleteFileButton.setOnAction(e -> handleDeleteFile());

        layout.getChildren().add(createDeleteButton(primaryStage));

        layout.getChildren().add(deleteFileButton);

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

    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Default style
        String defaultStyle = createButtonStyle("#ffcc00", "#ff9900", "black");
        String hoverStyle = createButtonStyle("#784800", "#943b00", "white");

        // Apply default style initially
        button.setStyle(defaultStyle);

        // Change style on hover
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(defaultStyle));

        return button;
    }

    private static String createButtonStyle(String topColor, String bottomColor, String textColor) {
        return "-fx-background-color: linear-gradient(to bottom, " + topColor + ", " + bottomColor + ");" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 20px;";
    }

    private static void handleDeleteFile() {
        // Show confirmation dialog
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete File");
        confirmationAlert.setHeaderText("Are you sure you want to delete your file?");
        confirmationAlert.setContentText("This action cannot be undone.");

        // Wait for user's response
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PlayerSavesService.deleteState();
            showMessage("File deletion is not implemented yet.");
        }
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
        // Add your slot buttons logic here
    }

    public static void main(String[] args) {
        launch(args);
    }


    private static Button createDeleteButton(Stage primaryStage) {
        Button deleteButton = new Button("Delete User File");
        deleteButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Default style
        String defaultStyle = createButtonStyle("#ff3333", "#cc0000", "white");
        String hoverStyle = createButtonStyle("#cc0000", "#990000", "yellow");

        deleteButton.setStyle(defaultStyle);
        deleteButton.setOnMouseEntered(e -> deleteButton.setStyle(hoverStyle));
        deleteButton.setOnMouseExited(e -> deleteButton.setStyle(defaultStyle));

        deleteButton.setOnAction(e -> {
            // Show confirmation alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Are you sure?");
            confirmationAlert.setContentText("This will delete your user file. This action cannot be undone.");

            // Wait for user response
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Confirmation received
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("File Deletion");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Your file has been successfully deleted! (Logic not implemented)");
                    successAlert.showAndWait();
                } else {
                    // User canceled
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