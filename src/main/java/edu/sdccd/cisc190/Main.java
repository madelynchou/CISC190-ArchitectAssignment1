package edu.sdccd.cisc190;

import edu.sdccd.cisc190.characters.*;
import edu.sdccd.cisc190.interfaces.MainMenu;
import edu.sdccd.cisc190.interfaces.Setup;
import edu.sdccd.cisc190.machines.TreasureSpins;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TreasureSpins treasureSpins = new TreasureSpins();
    static boolean isPlaying = true;
    static Bot[] bots = new Bot[]{new Chase(), new ProfessorHuang(), new MrBrooks(), new HondaBoyz()};

    //map menu options to numbers
    public enum MENU_OPTIONS {
        TREASURESPINS(1), DIAMONDDASH(2), MEGAMOOLAH(3), RAINBOWRICHES(4), HONDATRUNK(5), LEADERBOARD(6), QUIT(7);

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


    public static void main(String[] args) {
        Setup.launch(Setup.class, args);
        System.out.println(HumanPlayer.getInstance().getUsername());
        MainMenu.launch(MainMenu.class, args);
    }


}
//
//public class Main {
//
//    public static void main(String[] args) {
//        while (isPlaying) {
//            if (userProfile == null) {
//                System.out.println("Welcome to our casino!");
//                System.out.print("What's your name? ");
//                String name = scanner.nextLine();
//                userProfile = new HumanPlayer(name);
//            }
//
//            if (userProfile.money == 0) {
//                System.out.println("Game over!");
//                System.out.println("You just lost the house and the kids :(");
//                isPlaying = false;
//                break;
//            }
//            //print out user info
//            System.out.println("You're logged in as: " + User.name);
//            System.out.println("You have: $" + userProfile.money);
//
//            //display user options
//            for (MENU_OPTIONS option : MENU_OPTIONS.values()) {
//                System.out.println(option.getOptionNumber() + ": " + option);
//            }
//            //prompt user to select an option
//            System.out.print("Select an option (1-7): ");
//
//            //convert user input into int
//            int input = scanner.nextInt();
//
//            //create a variable that stores the option the user selects
//            MENU_OPTIONS selectedOption = null;
//
//            //iterate through the menu options and determine if the user inputs a valid option via comparing input vs. options in enum
//            try {
//                boolean validOption = false;
//                for (MENU_OPTIONS option : MENU_OPTIONS.values()) {
//                    if(option.getOptionNumber() == input) {
//                        selectedOption = option;
//                        validOption = true;
//                        break;
//                    }
//                }
//
//                //if user does not enter a valid option, throw an exception
//                if (!validOption) {
//                    throw new IllegalArgumentException();
//                }
//
//                //output based on user's VALID option selection
//                switch(selectedOption) {
//                    case TREASURESPINS:
//                        userProfile = TreasureSpins.init(userProfile);
//                        userProfile.addAmtHistory();
//
//                        for (User bot : bots) {
//                            int moneyChange = TreasureSpins.botPlay(bot);
//                            bot.adjustMoney(moneyChange);
//                        }
//                        break;
//                    case DIAMONDDASH:
//                        userProfile = DiamondDash.init(userProfile);
//                        userProfile.addAmtHistory();
//
//                        for (User bot : bots) {
//                            int moneyChange = DiamondDash.botPlay(bot);
//                            bot.adjustMoney(moneyChange);
//                        }
//                        break;
//                    case MEGAMOOLAH:
//                        System.out.println("Coming soon!");
//                        break;
//                    case QUIT:
//                        System.out.println("Come back soon!");
//                        System.out.println("99% of gamblers quit before making it big!");
//                        isPlaying = false;
//                        break;
//                    case RAINBOWRICHES:
//                        System.out.println("You have: $" + User.money);
//                        for (int i = 0; i < userProfile.amtHistory.size(); i++) {
//                            System.out.println(userProfile.amtHistory.get(i));
//                        }
//                        break;
//                    case HONDATRUNK:
//                        for (int i = 0; i < bots.length; i++) {
//                            System.out.println(bots[i].name + bots[i].money);
//                        }
//
//                }
//
//            } catch (InputMismatchException e) {
//                //tell the user to try again
//                System.out.println("That's not a valid input! Try again.");
//                scanner.next();
//            } catch (IllegalArgumentException e) {
//                System.out.println("Invalid option. Please select an option 1-4");
//            }
//        }
//    }
//}


