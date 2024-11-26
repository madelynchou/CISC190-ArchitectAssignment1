package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.players.bots.*;

import java.util.*;
import java.util.concurrent.*;

abstract public class Slot {
    public double luck; // Instance-specific luck
    public static String[] symbols; // Instance-specific symbols
    public static int maxBet; // Instance-specific max bet
    public static int minBet; // Instance-specific min bet
    public static double returnAmt; // Instance-specific return multiplier


    public int getMinBet() {
        return minBet;
    }

    public int getMaxBet() {
        return maxBet;
    }

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

    public void initializeSymbols() {}

    // -------------------------
    // Smaller private methods
    // -------------------------

    private static String[] generateSpunSymbols() {
        Random rand = new Random();
        String[] spunSlots = new String[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }
        return spunSlots;
    }

    private static int evaluateWinCondition(String[] arr) {
        if (arr[0].equals(arr[1]) && arr[1].equals(arr[2])) {
            return 3; // Full match
        } else {
            return 0;
        }
    }

    private static int calculatePayout(int moneyAmount, String[] spunRow, int bet) {
        int winningCondition = evaluateWinCondition(spunRow);
        return switch (winningCondition) {
            case 0 -> // No match
                    moneyAmount - bet;
            case 3 -> // Three-symbol match
                    (int) (moneyAmount + Math.floor(bet * returnAmt));
            default -> moneyAmount;
        };
    }

    public static int botPlay(Bot bot) {
        double betVarianceMultiplier = 0.8 + (Math.random() * 0.4); // Random number between 0.8 and 1.2
        int bet = (int) (bot.money * bot.aura * betVarianceMultiplier);

        float randomNumber = (float) (Math.random());

        int resultAmt;
        if (randomNumber <= bot.luck) {
            resultAmt = bet + bot.money;
        } else {
            resultAmt = bot.money - bet;
        }

        return resultAmt;
    }


}