package edu.sdccd.cisc190.machines;

/**
 * Treasure Spins is a type of slot in the casino
 * Uses the super constructor to set values of attributes inherited from Slots
 * High risk, high reward slot
 */
public class TreasureSpins extends Slot {
    public TreasureSpins() {
        super(new String[]{"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"}, 1000, 50, 20);
    }
}