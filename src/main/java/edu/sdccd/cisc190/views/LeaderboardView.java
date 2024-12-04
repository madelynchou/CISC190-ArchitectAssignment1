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

public class LeaderboardView extends Application {

    public static TableView<LeaderboardEntry> leaderboardTable;
    private final static ObservableList<LeaderboardEntry> entries = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // Listen to human player money changes
        HumanPlayer.getInstance().moneyProperty().addListener((_, _, _) -> updateLeaderboard());

        // Add listeners for all bot players
        AnitaMaxWynn.getInstance().moneyProperty().addListener((_, _, _) -> updateLeaderboard());
        HondaBoyz.getInstance().moneyProperty().addListener((_, _, _) -> updateLeaderboard());
        MrBrooks.getInstance().moneyProperty().addListener((_, _, _) -> updateLeaderboard());
        ProfessorHuang.getInstance().moneyProperty().addListener((_, _, _) -> updateLeaderboard());
        Chase.getInstance().moneyProperty().addListener((_, _, _) -> updateLeaderboard());

        showWindow(primaryStage);
    }

    private static void updateLeaderboard() {
        FXCollections.sort(entries, (entry1, entry2) -> Integer.compare(entry2.money().get(), entry1.money().get()));
        leaderboardTable.refresh();
    }

    public static void showWindow(Stage primaryStage) {
        VBox layout = createMainLayout();
        primaryStage.setTitle("Leaderboard");

        // Add header to the layout
        layout.getChildren().add(createHeader());

        // Create and populate TableView
        leaderboardTable = createLeaderboardTable();
        layout.getChildren().add(leaderboardTable);

        // Create and style the main menu button
        Button mainMenu = createStyledButton();
        mainMenu.setOnAction(_ -> MainMenuView.setupWindow(primaryStage));
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

    private static Button createStyledButton() {
        Button button = new Button("Main Menu");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle(createButtonStyle("#ffcc00", "#ff9900", "black"));

        button.setOnMouseEntered(_ -> button.setStyle(createButtonStyle("#ff9900", "#ff6600", "white")));
        button.setOnMouseExited(_ -> button.setStyle(createButtonStyle("#ffcc00", "#ff9900", "black")));

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
        public record LeaderboardEntry(String name, IntegerProperty money) {

    }
}