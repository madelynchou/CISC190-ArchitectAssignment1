package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.Slot;

public class TreasureSpins extends Slot {
    public TreasureSpins() {
        returnAmt = 10;
        symbols = new String[]{"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
    }

    @Override
    public void initializeSymbols() {
        symbols = new String[]{"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
    }
}