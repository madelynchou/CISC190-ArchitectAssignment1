package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.players.bots.*;
import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage);
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
        Button mainMenu = createStyledButton("Main Menu");
        mainMenu.setOnAction(event -> MainMenu.setupWindow(primaryStage));
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

        TableColumn<LeaderboardEntry, Integer> moneyColumn = new TableColumn<>("Money");
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        moneyColumn.setPrefWidth(150);

        // Add columns to the table
        table.getColumns().addAll(nameColumn, moneyColumn);

        // Populate and sort data
        table.setItems(getSortedLeaderboardData());

        return table;
    }

    private static ObservableList<LeaderboardEntry> getSortedLeaderboardData() {
        // Create observable list for leaderboard entries
        ObservableList<LeaderboardEntry> entries = FXCollections.observableArrayList();

        // Add bots to the leaderboard
        entries.add(new LeaderboardEntry(AnitaMaxWynn.getInstance().getName(), AnitaMaxWynn.getInstance().getMoney()));
        entries.add(new LeaderboardEntry(HondaBoyz.getInstance().getName(), HondaBoyz.getInstance().getMoney()));
        entries.add(new LeaderboardEntry(MrBrooks.getInstance().getName(), MrBrooks.getInstance().getMoney()));
        entries.add(new LeaderboardEntry(ProfessorHuang.getInstance().getName(), ProfessorHuang.getInstance().getMoney()));
        entries.add(new LeaderboardEntry(Chase.getInstance().getName(), Chase.getInstance().getMoney()));

        // Add HumanPlayer to the leaderboard
        HumanPlayer humanPlayer = HumanPlayer.getInstance();
        entries.add(new LeaderboardEntry(humanPlayer.getUsername(), humanPlayer.getMoney()));

        // Sort leaderboard by money in descending order
        FXCollections.sort(entries, (entry1, entry2) -> Integer.compare(entry2.getMoney(), entry1.getMoney()));

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
        private final Integer money;

        public LeaderboardEntry(String name, Integer money) {
            this.name = name;
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public Integer getMoney() {
            return money;
        }
    }
}