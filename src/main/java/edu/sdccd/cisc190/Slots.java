package edu.sdccd.cisc190;

import edu.sdccd.cisc190.characters.User;

import java.util.*;

public class Slots {
    static String[] symbols = {"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
    static int bet;
    static Scanner scanner = new Scanner(System.in);

    public static User main(User userProfile) {
        // Ask user how much they want to bet
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("How much do you wanna bet? (Input a number) $");
                bet = scanner.nextInt();

                //Determine if the user's desired bet is greater than the amount they currently have
                if (User.money < bet) {
                    System.out.printf("Your desired bet of $%d is greater than the amount of money you currently have. Please enter a valid bet.\n", bet);
                } else {
                    validInput = true; // Exit the loop if input is valid
                }

            } catch (InputMismatchException e) {
                System.out.println("That's not a number! Try again.");
                scanner.next();  // Clear the invalid input
            }
        }

        // Generates three random symbols by spinning, prints out resulting array
        String[] display = spin(symbols);
        System.out.println(Arrays.toString(display));

        // Checks if all symbols are the same
        boolean didWin = isWinner(display);

        // Returns different response based on whether row is winner
        if (didWin) {
            System.out.println("Wow! Good job you win! :D");
            // TODO: add a multiplier for how much the user wins
            System.out.println("You won $" + bet * 10);
            User.money += (bet * 10);
        } else {
            System.out.println("Oops, you didn't win :( Try again! 99% of gamblers quit before hitting big!");
            System.out.println("You lost $" + bet);
            User.money -= bet;
        }

        return userProfile;
        // TODO: add option to spin again

    }

    /*
    * Emulates spinning the slot machine
    * Generates three random symbols from the symbolsArray parameter
    * @param symbolArrays array of symbols that the method pulls randomly from
    * */
     static String[] spin(String[] symbolArray) {
        // Substantiate new Random() object
        Random rand = new Random();

        String[] spunSlots = new String[symbolArray.length];

        //generate a random index of the symbols array and modify original array
        for (int i = 0; i < symbolArray.length; i++) {
            spunSlots[i] = symbolArray[rand.nextInt(symbolArray.length)];
        }
        return spunSlots;
    }

    /*
    * Checks if the array elements in the array are the same
    * Used to verify if a spin was a winner
    * @param isWinner array of symbols that the method checks if all elements are the same
    * */
     static boolean isWinner(String[] arr) {
        //create a HashSet winningSet that stores all elements in winningRow
        HashSet<String> winningSet = new HashSet<>(Arrays.asList(arr));

        //return if the size of the hashset is 1 (all values in HashSet are the same)
        return winningSet.size() == 1;
    }
}
