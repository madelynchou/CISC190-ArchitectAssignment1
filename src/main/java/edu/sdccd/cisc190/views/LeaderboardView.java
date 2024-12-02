package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.players.bots.*;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * LeaderboardView is a JavaFX application that displays a leaderboard
 * showing the names and money amounts of players (both human and bot).
 * It dynamically updates when players' money values change.
 * */
public class LeaderboardView extends Application {

    public static TableView<LeaderboardEntry> leaderboardTable; // TableView to display the leaderboard entries
    private static ObservableList<LeaderboardEntry> entries = FXCollections.observableArrayList(); // Observable list to hold and manage leaderboard entries

    /**
     * Initalizes and starts the JavaFX application
     * @param primaryStage the primary stage for this application
     * */
    @Override
    public void start(Stage primaryStage) {
        // Set up listeners for money property changes to update the leaderboard.
        HumanPlayer.getInstance().moneyProperty().addListener((obs, oldVal, newVal) -> updateLeaderboard());
        AnitaMaxWynn.getInstance().moneyProperty().addListener((obs, oldVal, newVal) -> updateLeaderboard());
        HondaBoyz.getInstance().moneyProperty().addListener((obs, oldVal, newVal) -> updateLeaderboard());
        MrBrooks.getInstance().moneyProperty().addListener((obs, oldVal, newVal) -> updateLeaderboard());
        ProfessorHuang.getInstance().moneyProperty().addListener((obs, oldVal, newVal) -> updateLeaderboard());
        Chase.getInstance().moneyProperty().addListener((obs, oldVal, newVal) -> updateLeaderboard());

        showWindow(primaryStage);
    }

    /**
     * Updates and sorts the leaderboard based on players' money values.
     * */
    private static void updateLeaderboard() {
        FXCollections.sort(entries, (entry1, entry2) -> Integer.compare(entry2.getMoney().get(), entry1.getMoney().get()));
        leaderboardTable.refresh();
    }

    /**
     * Displays the leaderboard window.
     * @param primaryStage the primary stage to display the leaderboard.
     * */
    public static void showWindow(Stage primaryStage) {
        VBox layout = createMainLayout();
        primaryStage.setTitle("Leaderboard");

        // Add header to the layout
        layout.getChildren().add(createHeader());

        // Create and populate TableView
        leaderboardTable = createLeaderboardTable();
        layout.getChildren().add(leaderboardTable);

        // Create and style the main menu button
        Button mainMenu = createStyledButton("Main Menu");
        mainMenu.setOnAction(event -> MainMenuView.setupWindow(primaryStage));
        layout.getChildren().add(mainMenu);

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
        Text header = new Text("Leaderboard");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        header.setFill(Color.GOLD);
        return header;
    }

    private static TableView<LeaderboardEntry> createLeaderboardTable() {
        TableView<LeaderboardEntry> table = new TableView<>();
        table.setPrefHeight(300);

        // Define columns
        TableColumn<LeaderboardEntry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(150);

        TableColumn<LeaderboardEntry, IntegerProperty> moneyColumn = new TableColumn<>("Money");
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        moneyColumn.setPrefWidth(150);

        // Add columns to the table
        table.getColumns().addAll(nameColumn, moneyColumn);

        // Populate and sort data
        table.setItems(getSortedLeaderboardData());

        return table;
    }

    private static ObservableList<LeaderboardEntry> getSortedLeaderboardData() {
        // Ensure that this method populates the list correctly and considers all players.
        if (entries.isEmpty()) {
            entries.addAll(
                    new LeaderboardEntry(HumanPlayer.getInstance().getName(), HumanPlayer.getInstance().moneyProperty()),
                    new LeaderboardEntry(AnitaMaxWynn.getInstance().getName(), AnitaMaxWynn.getInstance().moneyProperty()),
                    new LeaderboardEntry(Chase.getInstance().getName(), Chase.getInstance().moneyProperty()),
                    new LeaderboardEntry(HondaBoyz.getInstance().getName(), HondaBoyz.getInstance().moneyProperty()),
                    new LeaderboardEntry(MrBrooks.getInstance().getName(), MrBrooks.getInstance().moneyProperty()),
                    new LeaderboardEntry(ProfessorHuang.getInstance().getName(), ProfessorHuang.getInstance().moneyProperty())
            );
        }
        return entries;
    }

    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle(createButtonStyle("#ffcc00", "#ff9900", "black"));

        button.setOnMouseEntered(e -> button.setStyle(createButtonStyle("#ff9900", "#ff6600", "white")));
        button.setOnMouseExited(e -> button.setStyle(createButtonStyle("#ffcc00", "#ff9900", "black")));

        return button;
    }

    private static String createButtonStyle(String topColor, String bottomColor, String textColor) {
        return "-fx-background-color: linear-gradient(to bottom, " + topColor + ", " + bottomColor + ");" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 20px;";
    }

    private static void setupScene(Stage primaryStage, VBox layout) {
        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Nested class for leaderboard entry
    public static class LeaderboardEntry {
        private final String name;
        private final IntegerProperty money;

        public LeaderboardEntry(String name, IntegerProperty money) {
            this.name = name;
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public IntegerProperty getMoney() {
            return money;
        }

        public IntegerProperty moneyProperty() {
            return money;
        }
    }
}