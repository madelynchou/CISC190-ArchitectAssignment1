package edu.sdccd.cisc190.players.bots;

/**
 * Chase is a bot that will be playing in the background
 * instantiate a new instance of Chase to implement in the application
 * low luck and aura = low capacity for winning
 */
public class Chase extends Bot {

    private static final Chase instance = new Chase();

    private Chase() {
        super("Chase Allan", 1000, 0.25, 0.1); // Initial money, luck, and aura values
    }

    public static Chase getInstance() {
        return instance;
    }


}
