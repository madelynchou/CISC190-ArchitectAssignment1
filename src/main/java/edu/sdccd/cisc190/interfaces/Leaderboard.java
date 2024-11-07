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
import javafx.stage.Stage;

public class Leaderboard extends Application {

    public static TableView<Player> leaderboardTable;

    @Override
    public void start(Stage primaryStage) {
    }

    public static void showWindow(Stage primaryStage) {
        primaryStage.setTitle("Leaderboard");

        // Define columns for rank, name, and score
        TableColumn<Player, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Initialize the TableView and add columns
        leaderboardTable = new TableView<>();
        leaderboardTable.getColumns().addAll(rankColumn, nameColumn, scoreColumn);

        // Add sample data to the leaderboard
        leaderboardTable.setItems(getSampleData());

        // Set layout and add TableView
        VBox layout = new VBox(10, leaderboardTable);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(400, 300);  // Adjust as needed

        Scene scene = new Scene(layout);
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