package edu.sdccd.cisc190;

import java.util.*;

abstract public class Slot {
    public double luck; // Instance-specific luck
    public static String[] symbols; // Instance-specific symbols
    public int maxBet; // Instance-specific max bet
    public int minBet; // Instance-specific min bet
    public static int returnAmt; // Instance-specific return multiplier
    static Scanner scanner = new Scanner(System.in); // Shared scanner
    public double bet; // Instance-specific bet amount
    public Bot bot; // Instance-specific bot

    public Slot() {
    }



    public static String[] spin() {
        Random rand = new Random();
        String[] spunSlots = new String[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }

        return spunSlots;
    }

    public static int checkWinType(String[] arr) {
        // Returns 2 for a two-symbol match, 3 for a three-symbol match, or 0 for no match
        if (arr[0].equals(arr[1]) && arr[1].equals(arr[2])) {
            return 3; // Full match
        } else if (arr[0].equals(arr[1]) || arr[1].equals(arr[2]) || arr[0].equals(arr[2])) {
            return 2; // Partial (two-symbol) match
        }
        return 0; // No match
    }

    public static int checkIfWon(int moneyAmount, String[] spunRow, int bet) {
        int winningCondition = checkWinType(spunRow);
        if (winningCondition == 0) {
            moneyAmount -= bet;
        } else if (winningCondition == 2) {
            moneyAmount += bet * (returnAmt / 2);
        } else if (winningCondition == 3) {
            moneyAmount += bet;
        } else {
            return moneyAmount;
        }
        return  moneyAmount;
    }
}