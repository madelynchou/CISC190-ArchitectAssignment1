package edu.sdccd.cisc190.views;

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

    public static TableView<Bot> leaderboardTable;

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

    private static TableView<Bot> createLeaderboardTable() {
        TableView<Bot> table = new TableView<>();
        table.setPrefHeight(300);

        // Define columns
        TableColumn<Bot, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(150);

        TableColumn<Bot, Integer> moneyColumn = new TableColumn<>("Money");
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        moneyColumn.setPrefWidth(150);

        // Add columns to the table
        table.getColumns().addAll(nameColumn, moneyColumn);

        // Populate and sort data
        table.setItems(getSortedBotsData());

        return table;
    }

    private static ObservableList<Bot> getSortedBotsData() {
        ObservableList<Bot> bots = FXCollections.observableArrayList(
                AnitaMaxWynn.getInstance(),
                HondaBoyz.getInstance(),
                MrBrooks.getInstance(),
                ProfessorHuang.getInstance(),
                Chase.getInstance()
        );

        // Sort bots by money in descending order
        FXCollections.sort(bots, (bot1, bot2) -> Integer.compare(bot2.getMoney(), bot1.getMoney()));

        return bots;
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
}