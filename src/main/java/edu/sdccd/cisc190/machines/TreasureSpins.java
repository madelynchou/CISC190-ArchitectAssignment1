package edu.sdccd.cisc190.machines;

public class TreasureSpins extends Slots {
    public TreasureSpins() {
        luck = 0.5;
        returnAmt = 10;
        symbols = new String[]{"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
    }

    @Override
    public String[] spin() {
        return new String[0];
    }
}