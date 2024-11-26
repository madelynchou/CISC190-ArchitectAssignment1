package edu.sdccd.cisc190.machines;

public class DiamondDash extends Slot {
    public DiamondDash() {
        returnAmt = 2;
        minBet = 15;
        maxBet = 1000;
        symbols = new String[]{"💍", "💠", "💎"};
    }
}