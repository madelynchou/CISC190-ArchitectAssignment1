package edu.sdccd.cisc190.interfaces;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Leaderboard extends Application {

    public static TableView<Player> leaderboardTable;

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage);
    }

    public static void showWindow(Stage primaryStage) {
        primaryStage.setTitle("Casino Royale - Leaderboard");

        // Define columns for rank, name, and score with styling
        TableColumn<Player, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        rankColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");

        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");

        TableColumn<Player, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");

        // Initialize the TableView and add columns
        leaderboardTable = new TableView<>();
        leaderboardTable.getColumns().addAll(rankColumn, nameColumn, scoreColumn);
        leaderboardTable.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: gold; " +
                        "-fx-border-width: 2px; " +
                        "-fx-font-size: 14px; -fx-font-family: 'Arial';"
        );

        // Add sample data to the leaderboard
        leaderboardTable.setItems(getSampleData());
        leaderboardTable.setPrefHeight(250);

        // Title label
        javafx.scene.control.Label titleLabel = new javafx.scene.control.Label("Leaderboard");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.GOLD);

        // Set layout and add TableView
        VBox layout = new VBox(20, titleLabel, leaderboardTable);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" +
                        "-fx-padding: 30px;"
        );

        Scene scene = new Scene(layout, 600, 400); // Adjusted size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Sample data for demonstration purposes
    private static ObservableList<Player> getSampleData() {
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.add(new Player(1, "Prof. Huang", 3000));
        players.add(new Player(2, "Jayden", 2750));
        players.add(new Player(3, "Chase", 2600));
        players.add(new Player(4, "Mr. Brooks", 2500));
        players.add(new Player(5, "Honda Boyz", 2400));
        return players;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Player class to represent leaderboard entries
    public static class Player {
        private final int rank;
        private final String name;
        private final int score;

        public Player(int rank, String name, int score) {
            this.rank = rank;
            this.name = name;
            this.score = score;
        }

        public int getRank() {
            return rank;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}