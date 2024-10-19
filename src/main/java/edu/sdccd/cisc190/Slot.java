package edu.sdccd.cisc190;

import edu.sdccd.cisc190.characters.User;

import java.util.*;

public class Slot {
    private static float luck;
    private static String[] symbols;
    private static int maxBet;
    private static int minBet;
    static Scanner scanner = new Scanner(System.in);
    public Slot() {
        }

    public static void init(User UserProfile) {
        boolean validInput = false;
        int bet;

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
        }

    public static String[] spin() {
        // Substantiate new Random() object
        Random rand = new Random();

        String[] spunSlots = new String[symbols.length];

        //generate a random index of the symbols array and modify original array
        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }

        return spunSlots;

    }

    static boolean isWinner(String[] arr) {
        //create a HashSet winningSet that stores all elements in winningRow
        HashSet<String> winningSet = new HashSet<>(Arrays.asList(arr));

        //return if the size of the hashset is 1 (all values in HashSet are the same)
        return winningSet.size() == 1;
    }

}
