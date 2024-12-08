package edu.sdccd.cisc190.players.bots;

/**
 * Honda Boyz is a bot that will be playing in the background
 * instantiate a new instance of Honda Boyz to implement in the application
 * Max luck and min aura = lowest chances of winning
 */
public class HondaBoyz extends Bot {
    private static final HondaBoyz instance = new HondaBoyz();

    private HondaBoyz() {
        super("HondaBoyz", 1000, 1.0, 0.1); // Initial money, luck, and aura values
    }

    public static HondaBoyz getInstance() {
        return instance;
    }


}
