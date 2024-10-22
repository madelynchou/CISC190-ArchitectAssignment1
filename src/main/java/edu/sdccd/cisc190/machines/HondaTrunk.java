package edu.sdccd.cisc190.machines;

public class HondaTrunk extends Slot {
    public HondaTrunk() {
        luck = 0.1;
        returnAmt = 1;
        symbols = new String[]{"🚗", "🛻", "🚕"};
    }

    @Override
    public String[] spin() {
        return new String[0];
    }

}
