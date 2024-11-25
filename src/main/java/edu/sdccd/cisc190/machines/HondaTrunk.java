package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.machines.Slot;

public class HondaTrunk extends Slot {
    public HondaTrunk() {
        returnAmt = 1.5;
        symbols = new String[]{"🚗", "🛻", "🚕"};
        minBet = 5;
        maxBet = 1000;
    }


}
