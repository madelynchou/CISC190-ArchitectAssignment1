package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.machines.Slot;

public class MegaMoolah extends Slot {
    @Override
    public void initializeSymbols() {
        returnAmt = 3;
        symbols = new String[]{"\uD83D\uDCB0", "\uD83E\uDD11", "\uD83D\uDCB8"};
    }

}
