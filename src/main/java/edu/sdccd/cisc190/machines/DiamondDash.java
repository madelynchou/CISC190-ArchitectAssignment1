package edu.sdccd.cisc190.machines;

import edu.sdccd.cisc190.Slot;

public class DiamondDash extends Slot {
    public static void initializeSymbols() {
        symbols = new String[]{"💍", "💠", "💎"};
    }



    public DiamondDash() {
        returnAmt = 10;
    }
}