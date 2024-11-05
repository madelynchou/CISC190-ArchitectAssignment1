package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.HumanPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Setup extends Application {
    static String userName;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Name Input");
        Label nameLabel = new Label("User Name");
        TextField nameField = new TextField();
        nameField.setPromptText("Your Name");

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            System.out.println(userName);
             HumanPlayer tempPlayer = HumanPlayer.getInstance();
             userName = nameField.getText();
             tempPlayer.setUsername(userName);
             primaryStage.close();

             Stage newWindow = new Stage();
             MainMenu.showWindow(newWindow);
        });

        // Layout for components
        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(nameLabel, nameField, submitButton);

        // Scene and Stage setup
        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
