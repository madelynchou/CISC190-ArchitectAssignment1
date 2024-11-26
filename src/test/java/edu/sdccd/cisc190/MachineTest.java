package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    @Test
    void isDiamondDashChildOfSlot() {
        DiamondDash diamondDash = new DiamondDash();

        assertInstanceOf(Slot.class, diamondDash);

        assertEquals(15, diamondDash.getMinBet());
        assertEquals(1000, diamondDash.getMaxBet());
    }

    @Test
    void isHondaTrunkChildOfSlot() {
        HondaTrunk hondaTrunk = new HondaTrunk();

        assertInstanceOf(Slot.class, hondaTrunk);

        assertEquals(5, hondaTrunk.getMinBet());
        assertEquals(1000, hondaTrunk.getMaxBet());
    }

    @Test
    void isMegaMoolahChildOfSlot() {
        MegaMoolah megaMoolah = new MegaMoolah();

        assertInstanceOf(Slot.class, megaMoolah);

        assertEquals(10, megaMoolah.getMinBet());
        assertEquals(1000, megaMoolah.getMaxBet());
    }

    @Test
    void isRainbowRichesChildOfSlot() {
        RainbowRiches rainbowRiches = new RainbowRiches();

        assertInstanceOf(Slot.class, rainbowRiches);

        assertEquals(25, rainbowRiches.getMinBet());
        assertEquals(1000, rainbowRiches.getMaxBet());
    }

    @Test
    void isTreasureSpinsChildOfSlot() {
        TreasureSpins treasureSpins = new TreasureSpins();

        assertInstanceOf(Slot.class, treasureSpins);

        assertEquals(50, treasureSpins.getMinBet());
        assertEquals(1000, treasureSpins.getMaxBet());
    }

}