package edu.sdccd.cisc190.machines;

/**
 * Rainbow Riches is a type of slot in the casino
 * Uses the super constructor to set values of attributes inherited from Slots
 * medium to high risk, medium to high reward slot
 */
public class RainbowRiches extends Slot {
    public RainbowRiches() {
        super(new String[]{"\uD83C\uDF08", "\uD83C\uDF27", "\uD83C\uDF24"}, 1000, 25, 5);
    }
}
