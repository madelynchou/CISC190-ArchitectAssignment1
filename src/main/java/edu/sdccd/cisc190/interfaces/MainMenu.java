package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.HumanPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static final String APP_NAME_FILE = "AppName.txt";


        @Override
        public void start(Stage primaryStage) {
        }


        public static void showWindow(Stage primaryStage) {
            primaryStage.setTitle("Menu Options");

            // Creating menu buttons
            Text usernameLabel = new Text(10, 50, "Username: " + HumanPlayer.getInstance().getUsername());
            Text moneyLabel = new Text(10, 50, "Money: " + HumanPlayer.getInstance().getMoney().toString());
            Button option1Button = new Button("Diamond Dash");
            Button option2Button = new Button("Honda Trunk");
            Button option3Button = new Button("Mega Moolah");
            Button option4Button = new Button("Rainbow Riches");
            Button option5Button = new Button("Treasure Spins");

            // Layout to hold buttons
            VBox layout = new VBox(10); // spacing between buttons
            layout.getChildren().addAll(usernameLabel, moneyLabel, option1Button, option2Button, option3Button, option4Button, option5Button);

            // Scene and Stage setup
            Scene scene = new Scene(layout, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.show();

            option1Button.setOnAction(e -> {
                Stage newWindow = new Stage();
                SlotMachine.showWindow(newWindow);

            });


        }

    private static void displayMessage(String message) {
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