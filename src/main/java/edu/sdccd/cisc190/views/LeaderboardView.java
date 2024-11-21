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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LeaderboardView extends Application {

    public static TableView<Bot> leaderboardTable;

    @Override
    public void start(Stage primaryStage) {
        showWindow(primaryStage);
    }

    public static void showWindow(Stage primaryStage) {
        // Create the TableView for bots
        leaderboardTable = new TableView<>();
        leaderboardTable.setPrefHeight(300);

        // Define columns for bot name and money amount
        TableColumn<Bot, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(150);

        TableColumn<Bot, Integer> moneyColumn = new TableColumn<>("Money");
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        moneyColumn.setPrefWidth(150);

        // Add columns to the TableView
        leaderboardTable.getColumns().addAll(nameColumn, moneyColumn);

        // Populate TableView with bots
        leaderboardTable.setItems(getBotsData());

        // Create a main menu button
        Button mainMenu = new Button("Main Menu");
        mainMenu.setOnAction(event -> {
            MainMenu.setupWindow(primaryStage);
        });

        // Layout setup
        VBox layout = new VBox(20, leaderboardTable, mainMenu);
        Scene scene = new Scene(layout, 400, 400); // Adjusted size
        primaryStage.setScene(scene);
        primaryStage.setTitle("Leaderboard");
        primaryStage.show();
    }

    // Create sample data for bots
    private static ObservableList<Bot> getBotsData() {
        return FXCollections.observableArrayList(
                AnitaMaxWynn.getInstance(),
                HondaBoyz.getInstance(),
                MrBrooks.getInstance(),
                ProfessorHuang.getInstance(),
                Chase.getInstance()
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}