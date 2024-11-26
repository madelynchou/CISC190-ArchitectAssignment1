package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.machines.Slot;

public class RainbowRiches extends Slot {
    @Override
    public void initializeSymbols() {
        returnAmt = 5;
        symbols = new String[]{"\uD83C\uDF08", "\uD83C\uDF27\uFE0F", "\uD83C\uDF24\uFE0F"};
    }

}
