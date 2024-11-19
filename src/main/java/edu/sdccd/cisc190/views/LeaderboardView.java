package edu.sdccd.cisc190.views;

import edu.sdccd.cisc190.players.bots.*;
import javafx.application.Application;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class LeaderboardView extends Application {

    public static TableView<Bot> leaderboardTable;

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage);
    }

    public static void showWindow(Stage primaryStage) {

    }
//        primaryStage.setTitle("Casino Royale - Leaderboard");
//
//        // Define columns for rank, name, and score with styling
//        TableColumn<Bot, Integer> rankColumn = new TableColumn<>("Rank");
//        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
//        rankColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
//
//        TableColumn<Bot, String> nameColumn = new TableColumn<>("Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        nameColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
//
//        TableColumn<Bot, Integer> scoreColumn = new TableColumn<>("Score");
//        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
//        scoreColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
//
//        // Initialize the TableView and add columns
//        leaderboardTable = new TableView<>();
//        leaderboardTable.getColumns().addAll(rankColumn, nameColumn, scoreColumn);
//        leaderboardTable.setStyle(
//                "-fx-background-color: transparent; " +
//                        "-fx-border-color: gold; " +
//                        "-fx-border-width: 2px; " +
//                        "-fx-font-size: 14px; -fx-font-family: 'Arial';"
//        );
//
//        // Add sample data to the leaderboard
//        leaderboardTable.setItems(getSampleData());
//        leaderboardTable.setPrefHeight(250);
//
//        // Title label
//        javafx.scene.control.Label titleLabel = new javafx.scene.control.Label("Leaderboard");
//        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
//        titleLabel.setTextFill(Color.GOLD);
//
//        // Set layout and add TableView
//        VBox layout = new VBox(20, titleLabel, leaderboardTable);
//        layout.setAlignment(Pos.CENTER);
//        layout.setStyle(
//                "-fx-background-color: linear-gradient(to bottom, #000000, #660000);" +
//                        "-fx-padding: 30px;"
//        );
//
//        Scene scene = new Scene(layout, 600, 400); // Adjusted size
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    // Sample data for demonstration purposes
//    private static ObservableList<Bot> getSampleData() {
//        ObservableList<Bot> players = FXCollections.observableArrayList();
//        players.add(Chase.getInstance());
//        players.add(HondaBoyz.getInstance());
//        players.add(ProfessorHuang.getInstance());
//        players.add(MrBrooks.getInstance());
//        return players;
//    }
//
    public static void main(String[] args) {
        launch(args);
    }

}