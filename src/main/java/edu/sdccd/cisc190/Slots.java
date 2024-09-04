package edu.sdccd.cisc190;

import java.util.*;

public class Slots {
    static final String[] symbols = {"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A", "\uD83C\uDF47", "\uD83C\uDF52", "\uD83C\uDF4E", "\uD83E\uDD51"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much do you wanna bet? ");
        Integer bet = scanner.nextInt();

        String[] winningRow = random(symbols);
        System.out.println(Arrays.toString(winningRow));

        boolean didWin = isWinner(winningRow);
        if (didWin) {
            System.out.println("Wow! Good job you win! :D");
            System.out.println("You won $" + bet);
        } else {
            System.out.println("Oops, you didn't win :( Try again! 99% of gamblers quit before hitting big!");
            System.out.println("You lost $" + bet);

        }

    }

    public static String[] random(String[] arrayargs) {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(5);
        int rand_int2 = rand.nextInt(5);
        int rand_int3 = rand.nextInt(5 );

        String column1 = arrayargs[rand_int1]; // Generates a random index
        String column2 = arrayargs[rand_int2]; // Generates a random index
        String column3 = arrayargs[rand_int3]; // Generates a random index
        return new String[]{column1, column2, column3}; // Gets the random value
    }

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
