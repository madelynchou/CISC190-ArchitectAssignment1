package edu.sdccd.cisc190.players.bots;

/**
 * Anita Max Wynn is a bot that will be playing in the background
 * instantiate a new instance of Anita Max Wynn to implement in the application
 * High luck, low aura = decent chances of winning
 */
public class AnitaMaxWynn extends Bot {
    private static final AnitaMaxWynn instance = new AnitaMaxWynn();

    private AnitaMaxWynn() {
        super("Anita Max Wynn", 1000, 0.8, 0.3); // Initial money, luck, and aura values
    }

    public static AnitaMaxWynn getInstance() {
        return instance;
    }
}
