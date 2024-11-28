package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.HondaTrunk;
import edu.sdccd.cisc190.machines.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HondaTrunkTest {

    @Test
    void isHondaTrunkChildOfSlot() {
        HondaTrunk hondaTrunk = new HondaTrunk();

        assertInstanceOf(Slot.class, hondaTrunk);

        String[] expectedHTSymbols = {"🚗", "🛻", "🚕"};

        assertArrayEquals(expectedHTSymbols, hondaTrunk.getSymbols());
        assertEquals(1, hondaTrunk.getMinBet());
        assertEquals(1000, hondaTrunk.getMaxBet());
        assertEquals(1.5, hondaTrunk.getReturnAmt());
    }
}