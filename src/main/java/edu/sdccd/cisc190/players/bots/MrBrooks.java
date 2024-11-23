package edu.sdccd.cisc190.players.bots;

public class MrBrooks extends Bot {
    private static MrBrooks instance;

    public MrBrooks() {
        this.name = "Mr.Brooks";
        this.money = 100;
        this.aura = 0.5;
        this.luck = 0.7;
    }

    public static MrBrooks getInstance() {

        if (instance == null) {
            instance = new MrBrooks();
        }
        return instance;
    }

}
