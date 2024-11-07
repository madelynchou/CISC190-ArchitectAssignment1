package edu.sdccd.cisc190;

import java.util.*;

abstract public class Slot {
    public double luck; // Instance-specific luck
    public static String[] symbols; // Instance-specific symbols
    public int maxBet; // Instance-specific max bet
    public int minBet; // Instance-specific min bet
    public int returnAmt; // Instance-specific return multiplier
    static Scanner scanner = new Scanner(System.in); // Shared scanner
    public double bet; // Instance-specific bet amount
    public Bot bot; // Instance-specific bot

    public Slot() {
    }

    public Bot init(Bot botProfile) {
        boolean validInput = false;
        this.bot = botProfile;

        while (!validInput) {
            try {
                System.out.print("How much do you wanna bet? (Input a number) $");
                this.bet = scanner.nextInt();

                if (botProfile.money < this.bet) {
                    System.out.printf("Your desired bet of $%d is greater than the amount of money you currently have. Please enter a valid bet.\n", this.bet);
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("That's not a number! Try again.");
                scanner.next();
            }
        }

        String[] spunRow = spin();
        System.out.println(Arrays.toString(spunRow));
        boolean isRowWinner = isWinner(spunRow);
        this.bot = ifWinner(isRowWinner, bot);
        return bot;
    }

    public static String[] spin() {
        Random rand = new Random();
        String[] spunSlots = new String[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            spunSlots[i] = symbols[rand.nextInt(symbols.length)];
        }

        return spunSlots;
    }

    public static boolean isWinner(String[] arr) {
        HashSet<String> winningSet = new HashSet<>(Arrays.asList(arr));
        return winningSet.size() == 1;
    }

    public Bot ifWinner(boolean didWin, Bot botProfile) {
        if (didWin) {
            System.out.println("Wow! Good job you win! :D");
            System.out.println("You won $" + this.bet * returnAmt);
            botProfile.money += (this.bet * returnAmt);
        } else {
            System.out.println("Oops, you didn't win :( Try again! 99% of gamblers quit before hitting big!");
            System.out.println("You lost $" + this.bet);
            botProfile.money -= this.bet;
        }

        return botProfile;
    }

    public int botPlay(Bot botProfile) {
        this.bet = minBet + (maxBet - minBet) * botProfile.aura;
        double randomNumber = Math.random();
        if (randomNumber > botProfile.luck) {
            botProfile.money -= this.bet;
        } else {
            botProfile.money += this.bet;
        }
        return botProfile.money;
    }
}