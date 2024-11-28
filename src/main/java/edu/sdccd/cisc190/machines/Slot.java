package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.players.bots.*;
import java.util.*;

abstract public class Slot {
    protected String[] symbols; // Instance-specific symbols
    protected int maxBet; // Instance-specific max bet
    protected int minBet; // Instance-specific min bet
    protected double returnAmt; // Instance-specific return multiplier

    public Slot(String[] symbols, int maxBet, int minBet, double returnAmt) {
        this.symbols = symbols;
        this.maxBet = maxBet;
        this.minBet = minBet;
        this.returnAmt = returnAmt;
    }

    public String[] getSymbols() {
        return symbols;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public int getMinBet() {
        return minBet;
    }

    public double getReturnAmt() {
        return returnAmt;
    }

    //method to generate the symbols
    public String[] generateSpunSymbols() {
        Random rand = new Random();
        String[] spunSlots = new String[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }
        return spunSlots;
    }

    //check if the displayed symbol is a full match, otherwise it has no match
    public int evaluateWinCondition(String[] arr) {
        if (arr[0].equals(arr[1]) && arr[1].equals(arr[2])) {
            return 3; // Full match
        } else {
            return 0;
        }
    }

    //if the user gets a full match, they earn their bet times the return multiplier of their slot, else they lose their bet
    public int calculatePayout(int moneyAmount, String[] spunRow, int bet) {
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
    public int botPlay(Bot bot) {
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