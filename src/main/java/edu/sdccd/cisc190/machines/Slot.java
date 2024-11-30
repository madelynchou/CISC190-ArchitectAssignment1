package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.players.HumanPlayer;
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

    // Returns the symbols for the slot machine
    public String[] getSymbols() {
        return symbols;
    }

    // Returns the maximum bet for the slot machine
    public int getMaxBet() {
        return maxBet;
    }

    // Returns the minimum bet for the slot machine
    public int getMinBet() {
        return minBet;
    }

    // Returns the jackpot's return amount for the slot machine
    public double getReturnAmt() {
        return returnAmt;
    }

    /**
    * Determines whether the user is able to bet an specific amount given their current balance and machine parameters
    * @param betAmt How much the user is attempting to bet
    * @return If the user's bet is within the bounds of their current balance and the minimum and maximum bet of the machine
    **/
    public boolean canBet(int betAmt) {
        int playerMoney = HumanPlayer.getInstance().getMoney();
        return betAmt <= playerMoney && betAmt >= this.getMinBet() && betAmt <= this.getMaxBet();
    }

    /**
     * Generates a random set of three symbols from the machine's symbols array
     * @return Random symbols from the machine's symbols array
    **/
    public String[] generateSpunSymbols() {
        Random rand = new Random();
        String[] spunSlots = new String[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }
        return spunSlots;
    }

    /**
     * Determines whether the user has won a jackpot by checking if all the symbols in the array are the same
     * @param arr Array of random symbols generated from the generateSpunSymbols() method
     * @return 3 if there is full match, 0 if there is no match
     * **/
    public int evaluateWinCondition(String[] arr) {
        if (arr[0].equals(arr[1]) && arr[1].equals(arr[2])) {
            return 3; // Full match
        } else {
            return 0;
        }
    }

    /**
     * Updates the user's balance based on the result of their spin
     * @param moneyAmount The amount of money the user currently has
     * @param bet The amount of money the user has bet
     * **/
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

    /**
     * For bot threads to simulate bots playing the game
     * @return resultAmt The bot's new money amount
     * **/
    public int botPlay(Bot bot) {
        double betVarianceMultiplier = 0.8 + (Math.random() * 0.4); // Random number between 0.8 and 1.2
        int bet = (int) (bot.getMoney() * bot.getAura() * betVarianceMultiplier); // Calculate the bot's bet as a function of its current money, aura and variance multiplier

        float randomNumber = (float) (Math.random());
        int resultAmt;

        /*
        * Generate a random number 0.0 - 1.0
        * If luck is greater than or equal to this variable, the bot wins.
        * If luck is less than this number, the bot loses
        * Bot's money amount is then adjusted accordingly
        * */
        if (randomNumber <= bot.getLuck()) {
            resultAmt = bet + bot.getMoney();
        } else {
            resultAmt = bot.getMoney() - bet;
        }

        return resultAmt;
    }
}