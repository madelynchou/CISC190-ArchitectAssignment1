package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.Slot;

public class HondaTrunk extends Slot {
    public HondaTrunk() {
        luck = 0.1;
        returnAmt = 1;
        symbols = new String[]{"🚗", "🛻", "🚕"};
    }
    @Override
    public void initializeSymbols() {
        symbols = new String[]{"🚗", "🛻", "🚕"};
    }


}
