package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.Slot;
import edu.sdccd.cisc190.machines.TreasureSpins;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureSpinsTest {

    @Test
    void isTreasureSpinsChildOfSlot() {
        TreasureSpins treasureSpins = new TreasureSpins();

        assertInstanceOf(Slot.class, treasureSpins);

        assertEquals(50, treasureSpins.getMinBet());
        assertEquals(1000, treasureSpins.getMaxBet());
        assertEquals(10, treasureSpins.getReturnAmt());

        String[] expectedTSSymbols = {"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
        assertArrayEquals(expectedTSSymbols, treasureSpins.getSymbols());
    }
}