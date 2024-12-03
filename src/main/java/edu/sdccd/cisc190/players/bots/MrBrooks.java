package edu.sdccd.cisc190.players.bots;

/**
 * Mr Brooks is a bot that will be playing in the background
 * instantiate a new instance of Mr Brooks to implement in the application
 * Decent luck and solid aura = decent chances for high winnings
 */
public class MrBrooks extends Bot {
    private static final MrBrooks instance = new MrBrooks();

    private MrBrooks() {
        super("MrBrooks", 1000, 0.5, 0.7); // Initial money, luck, and aura values
    }

    public static MrBrooks getInstance() {
        return instance;
    }


}
