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
        assertEquals(2, Slot.returnAmt);

        String[] expectedDDSymbols = {"💍", "💠", "💎"};
        assertArrayEquals(expectedDDSymbols, Slot.symbols);
    }

    @Test
    void isHondaTrunkChildOfSlot() {
        HondaTrunk hondaTrunk = new HondaTrunk();

        assertInstanceOf(Slot.class, hondaTrunk);

        assertEquals(1, hondaTrunk.getMinBet());
        assertEquals(1000, hondaTrunk.getMaxBet());

        String[] expectedHTSymbols = {"🚗", "🛻", "🚕"};
        assertArrayEquals(expectedHTSymbols, Slot.symbols);
    }

    @Test
    void isMegaMoolahChildOfSlot() {
        MegaMoolah megaMoolah = new MegaMoolah();

        assertInstanceOf(Slot.class, megaMoolah);

        assertEquals(10, megaMoolah.getMinBet());
        assertEquals(1000, megaMoolah.getMaxBet());

        String[] expectedMMSymbols = {"\uD83D\uDCB0", "\uD83E\uDD11", "\uD83D\uDCB8"};
        assertArrayEquals(expectedMMSymbols, Slot.symbols);
    }

    @Test
    void isRainbowRichesChildOfSlot() {
        RainbowRiches rainbowRiches = new RainbowRiches();

        assertInstanceOf(Slot.class, rainbowRiches);

        assertEquals(25, rainbowRiches.getMinBet());
        assertEquals(1000, rainbowRiches.getMaxBet());

        String[] expectedRRSymbols = {"\uD83C\uDF08", "\uD83C\uDF27", "\uD83C\uDF24"};
        assertArrayEquals(expectedRRSymbols, Slot.symbols);
    }

    @Test
    void isTreasureSpinsChildOfSlot() {
        TreasureSpins treasureSpins = new TreasureSpins();

        assertInstanceOf(Slot.class, treasureSpins);

        assertEquals(50, treasureSpins.getMinBet());
        assertEquals(1000, treasureSpins.getMaxBet());

        String[] expectedTSSymbols = {"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
        assertArrayEquals(expectedTSSymbols, Slot.symbols);
    }

}