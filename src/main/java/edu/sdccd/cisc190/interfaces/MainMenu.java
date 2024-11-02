package edu.sdccd.cisc190.interfaces;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static final String APP_NAME_FILE = "AppName.txt";


        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Menu Options");

            // Creating menu buttons
            Button option1Button = new Button("Option 1: View Profile");
            Button option2Button = new Button("Option 2: Settings");
            Button option3Button = new Button("Option 3: Logout");

            // Adding actions to buttons
            option1Button.setOnAction(e -> displayMessage("You selected: View Profile"));
            option2Button.setOnAction(e -> displayMessage("You selected: Settings"));
            option3Button.setOnAction(e -> displayMessage("You selected: Logout"));

            // Layout to hold buttons
            VBox layout = new VBox(10); // spacing between buttons
            layout.getChildren().addAll(option1Button, option2Button, option3Button);

            // Scene and Stage setup
            Scene scene = new Scene(layout, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void displayMessage(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }