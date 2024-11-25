package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.machines.Slot;

public class MegaMoolah extends Slot {
    public MegaMoolah() {
        returnAmt = 3;
        minBet = 10;
        maxBet = 1000;
        symbols = new String[]{"\uD83D\uDCB0", "\uD83E\uDD11", "\uD83D\uDCB8"};
    }

}
