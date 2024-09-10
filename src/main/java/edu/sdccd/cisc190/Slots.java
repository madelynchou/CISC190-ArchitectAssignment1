package edu.sdccd.cisc190;

import java.util.*;

public class Slots {
    static final String[] symbols = {"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A", "\uD83C\uDF47", "\uD83C\uDF52", "\uD83C\uDF4E", "\uD83E\uDD51"};

    public static void main(String[] args) {
        // Ask user how much they want to bet
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much do you wanna bet? ");
        Integer bet = scanner.nextInt();

        // Generates three random symbols by spinning, prints out resulting array
        String[] winningRow = spin(symbols);
        System.out.println(Arrays.toString(winningRow));

        // Checks if all symbols are the same
        boolean didWin = isWinner(winningRow);

        // Returns different response based on whether row is winner
        if (didWin) {
            System.out.println("Wow! Good job you win! :D");
            // TODO: add a multiplier for how much the user wins
            System.out.println("You won $" + bet);
        } else {
            System.out.println("Oops, you didn't win :( Try again! 99% of gamblers quit before hitting big!");
            System.out.println("You lost $" + bet);
        }

        // TODO: add option to save winning amounts
        // TODO: add option to spin again
        
    }

    /*
    * Emulates spinning the slot machine
    * Generates three random symbols from the symbolsArray parameter
    * @param symbolArrays array of symbols that the method pulls randomly from
    * */
    public static String[] spin(String[] symbolArrays) {
        // Substantiate new Random() object
        Random rand = new Random();

        // Generate three random indices for the symbolArrays parameter
        int rand_int1 = rand.nextInt(symbolArrays.length - 1);
        int rand_int2 = rand.nextInt(symbolArrays.length - 1);
        int rand_int3 = rand.nextInt(symbolArrays.length - 1);

        String column1 = symbolArrays[rand_int1]; // Generates a random symbol
        String column2 = symbolArrays[rand_int2]; // Generates a random symbol
        String column3 = symbolArrays[rand_int3]; // Generates a random symbol
        return new String[]{column1, column2, column3}; // Returns the result of the spinned row
    }

    /*
    * Checks if the array elements in the array are the same
    * Used to verify if a spin was a winner
    * @param isWinner array of symbols that the method checks if all elements are the same
    * */
    public static boolean isWinner(String[] arr) {
        if (arr.length == 0) {
            return true;  // An empty array can be considered as having all "same" values
        }

        String firstValue = arr[0];  // Get the first element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != firstValue) {
                return false;  // If any element is different, return false
            }
        }
        return true;  // All elements are the same
    }


}
