package edu.sdccd.cisc190.machines;

import java.util.*;

abstract public class Slot {
    public double luck; // Instance-specific luck
    public static String[] symbols; // Instance-specific symbols
    public int maxBet; // Instance-specific max bet
    public int minBet; // Instance-specific min bet
    public static int returnAmt; // Instance-specific return multiplier
    static Scanner scanner = new Scanner(System.in); // Shared scanner
    public double bet; // Instance-specific bet amount

    // Spins the slot machine symbols
    public static String[] spin() {
        return generateSpunSymbols();
    }

    // Determines the win type based on the spun symbols
    public static int checkWinType(String[] arr) {
        return evaluateWinCondition(arr);
    }

    // Adjusts the player's money based on whether they won or lost
    public static int checkIfWon(int moneyAmount, String[] spunRow, int bet) {
        return calculatePayout(moneyAmount, spunRow, bet);
    }

    // Initializes symbols for the slot machine (abstract for subclasses)
    public void initializeSymbols() {}

    // -------------------------
    // Smaller private methods
    // -------------------------

    // Generates the symbols that appear after spinning
    private static String[] generateSpunSymbols() {
        Random rand = new Random();
        String[] spunSlots = new String[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }
        return spunSlots;
    }

    // Evaluates win conditions based on the spun symbols
    private static int evaluateWinCondition(String[] arr) {
        if (arr[0].equals(arr[1]) && arr[1].equals(arr[2])) {
            return 3; // Full match
        } else if (arr[0].equals(arr[1]) || arr[1].equals(arr[2]) || arr[0].equals(arr[2])) {
            return 2; // Partial (two-symbol) match
        }
        return 0; // No match
    }

    // Calculates the player's new money amount based on the outcome
    private static int calculatePayout(int moneyAmount, String[] spunRow, int bet) {
        int winningCondition = evaluateWinCondition(spunRow);
        switch (winningCondition) {
            case 0: // No match
                return moneyAmount - bet;
            case 2: // Two-symbol match
                return moneyAmount + bet * (returnAmt / 2);
            case 3: // Three-symbol match
                return moneyAmount + bet;
            default:
                return moneyAmount;
        }
    }
}