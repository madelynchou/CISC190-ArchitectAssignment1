package edu.sdccd.cisc190.players.bots;

public class AnitaMaxWynn extends Bot {
    private static AnitaMaxWynn instance;

    public AnitaMaxWynn() {
        this.name = "AnitaMaxWynn";
        this.money = 100;
        this.aura = 0.9;
        this.luck = 0.6;
    }

    public static AnitaMaxWynn getInstance() {

        if (instance == null) {
            instance = new AnitaMaxWynn();
        }
        return instance;
    }

}
