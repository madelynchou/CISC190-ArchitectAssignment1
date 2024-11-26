package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    @Test
    void isDiamondDashChildOfSlot() {
        DiamondDash diamondDash = new DiamondDash();
        assertTrue(diamondDash instanceof Slot);
    }

    @Test
    void isHondaTrunkChildOfSlot() {
        HondaTrunk hondaTrunk = new HondaTrunk();
        assertTrue(hondaTrunk instanceof Slot);
    }

    @Test
    void isMegaMoolahChildOfSlot() {
        MegaMoolah megaMoolah = new MegaMoolah();
        assertTrue(megaMoolah instanceof Slot);
    }

    @Test
    void isRainbowRichesChildOfSlot() {
        RainbowRiches rainbowRiches = new RainbowRiches();
        assertTrue(rainbowRiches instanceof Slot);
    }

    @Test
    void isTreasureSpinsChildOfSlot() {
        TreasureSpins treasureSpins = new TreasureSpins();
        assertTrue(treasureSpins instanceof Slot);
    }

}