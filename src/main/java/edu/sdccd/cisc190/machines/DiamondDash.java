package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.machines.Slot;

public class DiamondDash extends Slot {
    public DiamondDash() {
        returnAmt = 2;
        minBet = 15;
        maxBet = 1000;
        symbols = new String[]{"💍", "💠", "💎"};
    }
}