package edu.sdccd.cisc190.players.bots;

import edu.sdccd.cisc190.players.HumanPlayer;

public class Chase extends Bot {
    private static Chase instance;

    private Chase() {
        this.name = "Chase";
        this.money = 1000;
        this.luck = 0.5;
        this.aura = 0.1;
    }

    public static Chase getInstance() {
        if (instance == null) {
            instance = new Chase();
        }
        return instance;
    }

}
