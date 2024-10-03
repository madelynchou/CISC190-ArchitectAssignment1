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
    //map menu options to numbers
    public enum MENU_OPTIONS {
        SLOTS(1), ROULETTE(2), BLACKJACK(3), QUIT(4);

        //option number
        private final int optionNumber;

        //associate option w its number
        MENU_OPTIONS(int optionNumber) {
            this.optionNumber = optionNumber;
        }

        //get option number
        public int getOptionNumber() {
            return optionNumber;
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static User userProfile = new User();
    static Slots slots = new Slots();
    static boolean isPlaying = true;

    public static void main(String[] args) {
        while (isPlaying) {
            if (userProfile.name == null) {
                System.out.println("Welcome to our casino!");
                System.out.print("What's your name? ");
                String name = scanner.nextLine();
                User.set(name, 100);
            }
            //print out user info
            System.out.println("You're logged in as: " + User.name);
            System.out.println("You have: $" + User.money);

            //display user options
            for (MENU_OPTIONS option : MENU_OPTIONS.values()) {
                System.out.println(option.getOptionNumber() + ": " + option);
            }
            //prompt user to select an option
            System.out.print("Select an option (1-4): ");

            //convert user input into int
            int input = scanner.nextInt();

            //create a variable that stores the option the user selects
            MENU_OPTIONS selectedOption = null;

            //iterate through the menu options and determine if the user inputs a valid option via comparing input vs. options in enum
            try {
                boolean validOption = false;
                for (MENU_OPTIONS option : MENU_OPTIONS.values()) {
                    if(option.getOptionNumber() == input) {
                        selectedOption = option;
                        validOption = true;
                        break;
                    }
                }

                //if user does not enter a valid option, throw an exception
                if (!validOption) {
                    throw new IllegalArgumentException();
                }

                //output based on user's VALID option selection
                switch(selectedOption) {
                    case SLOTS:
                        userProfile = Slots.main(userProfile);
                        break;
                    case ROULETTE:
                        System.out.println("Coming soon!");
                        break;
                    case BLACKJACK:
                        System.out.println("Coming soon!");
                        break;
                    case QUIT:
                        System.out.println("Come back soon!");
                        System.out.println("99% of gamblers quit before making it big!");
                        isPlaying = false;
                        break;
                }

            } catch (InputMismatchException e) {
                //tell the user to try again
                System.out.println("That's not a valid input! Try again.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid option. Please select an option 1-4");
            }
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

