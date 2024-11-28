package edu.sdccd.cisc190.machines;

public class TreasureSpins extends Slot {
    public TreasureSpins() {
        returnAmt = 10;
        symbols = new String[]{"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
        minBet = 50;
        maxBet = 1000;
    }
}