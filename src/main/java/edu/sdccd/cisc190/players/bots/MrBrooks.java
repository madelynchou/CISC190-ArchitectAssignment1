package edu.sdccd.cisc190.players.bots;

public class MrBrooks extends Bot {
    private static final MrBrooks instance = new MrBrooks();

    private MrBrooks() {
        super("MrBrooks", 1000, 0.5, 0.7); // Initial money, luck, and aura values
    }

    public static MrBrooks getInstance() {
        return instance;
    }


}
