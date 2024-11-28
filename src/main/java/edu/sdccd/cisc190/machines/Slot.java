package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.players.bots.*;
import java.util.*;

abstract public class Slot {
    public static String[] symbols; // Instance-specific symbols
    public static int maxBet; // Instance-specific max bet
    public static int minBet; // Instance-specific min bet
    public static double returnAmt; // Instance-specific return multiplier

    public int getMaxBet() {
        return maxBet;
    }

    public int getMinBet() {
        return minBet;
    }

    public void initializeSymbols() {}

    //method to generate the symbols
    public static String[] generateSpunSymbols() {
        Random rand = new Random();
        String[] spunSlots = new String[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }
        return spunSlots;
    }

    //check if the displayed symbol is a full match, otherwise it has no match
    public static int evaluateWinCondition(String[] arr) {
        if (arr[0].equals(arr[1]) && arr[1].equals(arr[2])) {
            return 3; // Full match
        } else {
            return 0;
        }
    }

    //if the user gets a full match, they earn their bet times the return multiplier of their slot, else they lose their bet
    public static int calculatePayout(int moneyAmount, String[] spunRow, int bet) {
        int winningCondition = evaluateWinCondition(spunRow);
        return switch (winningCondition) {
            case 0 -> // No match
                    moneyAmount - bet;
            case 3 -> // Three-symbol match
                    (int) (moneyAmount + Math.floor(bet * returnAmt));
            default -> moneyAmount;
        };
    }

    //create a bet amount for the bot using the bot's money, aura, and a randomly generated bet multiplier
    public static int botPlay(Bot bot) {
        double betVarianceMultiplier = 0.8 + (Math.random() * 0.4); // Random number between 0.8 and 1.2
        int bet = (int) (bot.getMoney() * bot.getAura() * betVarianceMultiplier);

        float randomNumber = (float) (Math.random());

        int resultAmt;
        if (randomNumber <= bot.getLuck()) {
            resultAmt = bet + bot.getMoney();
        } else {
            resultAmt = bot.getMoney() - bet;
        }

        return resultAmt;
    }
}