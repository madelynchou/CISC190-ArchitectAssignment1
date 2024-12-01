package edu.sdccd.cisc190.players.bots;

public class HondaBoyz extends Bot {
    private static final HondaBoyz instance = new HondaBoyz();

    private HondaBoyz() {
        super("HondaBoyz", 1000, 1.0, 0.1); // Initial money, luck, and aura values
    }

    public static HondaBoyz getInstance() {
        return instance;
    }


}
