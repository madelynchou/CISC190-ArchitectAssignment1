package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.DiamondDash;
import edu.sdccd.cisc190.machines.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiamondDashTest {

    @Test
    void isDiamondDashChildOfSlot() {
        DiamondDash diamondDash = new DiamondDash();

        assertInstanceOf(Slot.class, diamondDash);

        String[] expectedDDSymbols = {"💍", "💠", "💎"};

        assertArrayEquals(expectedDDSymbols, diamondDash.getSymbols());
        assertEquals(15, diamondDash.getMinBet());
        assertEquals(1000, diamondDash.getMaxBet());
        assertEquals(2, diamondDash.getReturnAmt());
    }
}