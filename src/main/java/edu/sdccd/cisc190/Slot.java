package edu.sdccd.cisc190;

import java.util.*;

abstract public class Slot {
    public static double luck;
    public static String[] symbols;
    public static int maxBet;
    public static int minBet;
    public static int returnAmt;
    static Scanner scanner = new Scanner(System.in);
    public static double bet;
    public static User user;

    public Slot() {
    }

    public static User init(User userProfile) {
        boolean validInput = false;
        user = userProfile;

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

        String[] spunRow = spin(symbols);
        System.out.println(Arrays.toString(spunRow));
        boolean isRowWinner = isWinner(spunRow);
        user = ifWinner(isRowWinner, user);
        return user;
    }

    public static String[] spin(String[] symbols) {
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

    static User ifWinner(boolean didWin, User userProfile) {
        if (didWin) {
            System.out.println("Wow! Good job you win! :D");
            // TODO: add a multiplier for how much the user wins
            System.out.println("You won $" + bet * returnAmt);
            userProfile.money += (bet * returnAmt);
        } else {
            System.out.println("Oops, you didn't win :( Try again! 99% of gamblers quit before hitting big!");
            System.out.println("You lost $" + bet);
            userProfile.money -= bet;
        }

        return userProfile;
    }

    public static int botPlay(User userProfile) {
        bet = minBet + (maxBet - minBet) * userProfile.aura;
        double randomNumber = Math.random();
        if (randomNumber > userProfile.luck) {
            userProfile.money -= bet;
        } else {
            userProfile.money += bet;
        }
        return userProfile.money;
    }

}