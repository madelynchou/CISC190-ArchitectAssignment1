package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int option;
    static User userProfile = new User();
    static Slots slots = new Slots();
    static boolean isPlaying = true;
    public static void main(String[] args) {
        userProfile.set("Chase Allen", 100);

        while (isPlaying) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.println("You're logged in as: " + userProfile.name);
                    System.out.println("You have: $" + userProfile.money);
                    System.out.println("1: Slots");
                    System.out.println("2: Roulette");
                    System.out.println("3: Blackjack");
                    System.out.println("4: Quit");
                    System.out.print("Select an option: ");
                    option = scanner.nextInt();
                    if (option == 1 || option == 2 || option == 3 || option == 4) {
                        validInput = true;  // Exit the loop if input is valid
                    } else {
                        validInput = false;
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("That's not a number! Try again.");
                    scanner.next();  // Clear the invalid input
                }
            }

            switch(option) {
                case 1:
                    userProfile = slots.main(userProfile);
                    break;
                case 2:
                    System.out.println("Coming soon!");
                    break;
                case 3:
                    System.out.println("Coming soon!");
                    break;
                case 4:
                    System.out.println("Come back soon!");
                    System.out.println("99% of gamblers quit before making it big!");
                    isPlaying = false;
                    break;
                default:
                    System.out.println("Something went wrong!");
            }
        }


    }


//    public static final String APP_NAME_FILE = "AppName.txt";
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public static String getAppName() throws IOException {
//        String appName;
//        try (InputStream is = Main.class.getClassLoader().getResourceAsStream(APP_NAME_FILE)) {
//            if(is == null) throw new IOException(APP_NAME_FILE + " could not be found!");
//            appName = new BufferedReader(new InputStreamReader(is)).readLine();
//        }
//
//        return appName;
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Label label = new Label("The content inside the TitledPane");
//        TitledPane = new TitledPane(getAppName(), label);
//        titledPane.setCollapsible(false);
//
//        titledPane.setExpanded(true);
//        titledPane.setExpanded(false);
//
//        Scene scene = new Scene(new VBox(titledPane));
//        stage.setScene(scene);
//
//        stage.show();
//    }
}
