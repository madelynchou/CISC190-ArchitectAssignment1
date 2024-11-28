package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.RainbowRiches;
import edu.sdccd.cisc190.machines.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RainbowRichesTest {

    @Test
    void isRainbowRichesChildOfSlot() {
        RainbowRiches rainbowRiches = new RainbowRiches();

        assertInstanceOf(Slot.class, rainbowRiches);

        assertEquals(25, rainbowRiches.getMinBet());
        assertEquals(1000, rainbowRiches.getMaxBet());
        assertEquals(5, rainbowRiches.getReturnAmt());

        String[] expectedRRSymbols = {"\uD83C\uDF08", "\uD83C\uDF27", "\uD83C\uDF24"};
        assertArrayEquals(expectedRRSymbols, rainbowRiches.getSymbols());
    }
}