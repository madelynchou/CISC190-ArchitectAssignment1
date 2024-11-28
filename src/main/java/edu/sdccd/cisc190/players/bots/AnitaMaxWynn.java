package edu.sdccd.cisc190.players.bots;

public class AnitaMaxWynn extends Bot {
    private static AnitaMaxWynn instance = new AnitaMaxWynn();

    private AnitaMaxWynn() {
        super("Anita Max Wynn", 1000, 0.8, 0.3); // Initial money, luck, and aura values
    }

    public static AnitaMaxWynn getInstance() {
        return instance;
    }
}
