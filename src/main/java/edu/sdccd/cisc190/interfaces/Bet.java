package edu.sdccd.cisc190.interfaces;

import edu.sdccd.cisc190.HumanPlayer;
import edu.sdccd.cisc190.Slot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Bet extends Application {
    static int betAmt;

    @Override
    public void start(Stage primaryStage) {

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showWindow(Stage primaryStage) {
        primaryStage.setTitle("Bet Amount");
        Label nameLabel = new Label("How much do you wanna bet?");
        TextField numericTextField = new TextField();
        numericTextField.setPromptText("Enter numbers only");

        // Add a listener to restrict input to digits only
        numericTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // \\d* matches zero or more digits
                numericTextField.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-numeric characters
            }
        });
        numericTextField.setPromptText("Your Name");

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            betAmt = Integer.parseInt(numericTextField.getText());

            primaryStage.close();


            Stage newWindow = new Stage();
            SlotMachine.showWindow(newWindow, betAmt);
        });

        // Layout for components
        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(nameLabel, numericTextField, submitButton);

        // Scene and Stage setup
        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
