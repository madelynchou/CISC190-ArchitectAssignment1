package edu.sdccd.cisc190.players.bots;

public class Chase extends Bot {
    private static Chase instance;

    private Chase() {
        this.name = "Chase";
        this.money = 100;
        this.luck = 0.25;
        this.aura = 0.1;
    }

    public static Chase getInstance() {
        if (instance == null) {
            instance = new Chase();
        }
        return instance;
    }

}
