package edu.sdccd.cisc190.players.bots;

public class Chase extends Bot {

    private static final Chase instance = new Chase();

    private Chase() {
        super("Chase Allan", 1000, 0.25, 0.1); // Initial money, luck, and aura values
    }

    public static Chase getInstance() {
        return instance;
    }


}
