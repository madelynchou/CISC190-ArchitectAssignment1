package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.MegaMoolah;
import edu.sdccd.cisc190.machines.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MegaMoolahTest {

    @Test
    void isMegaMoolahChildOfSlot() {
        MegaMoolah megaMoolah = new MegaMoolah();

        assertInstanceOf(Slot.class, megaMoolah);

        assertEquals(10, megaMoolah.getMinBet());
        assertEquals(1000, megaMoolah.getMaxBet());
        assertEquals(3, megaMoolah.getReturnAmt());

        String[] expectedMMSymbols = {"\uD83D\uDCB0", "\uD83E\uDD11", "\uD83D\uDCB8"};
        assertArrayEquals(expectedMMSymbols, megaMoolah.getSymbols());
    }
}