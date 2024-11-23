package edu.sdccd.cisc190.players.bots;

public class HondaBoyz extends Bot {
    private static HondaBoyz instance;

    public HondaBoyz() {
        this.name = "HondaBoyz";
        this.money = 100;
        this.aura = 1.0;
        this.luck = 0.1;
    }

    public static HondaBoyz getInstance() {

        if (instance == null) {
            instance = new HondaBoyz();
        }
        return instance;
    }

}
