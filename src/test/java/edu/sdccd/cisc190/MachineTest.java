package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MachineTest {

    @Test
    void isDiamondDashChildOfSlot() {
        DiamondDash diamondDash = new DiamondDash();

        //verify that slot machine game is an instance of a Slot
        assertInstanceOf(Slot.class, diamondDash);

        //verify that the attributes of the game are valid attributes of parent Slot
        assertEquals(15, diamondDash.getMinBet());
        assertEquals(1000, diamondDash.getMaxBet());
        assertEquals(2, diamondDash.getReturnAmt());

        String[] expectedDDSymbols = {"💍", "💠", "💎"};
        assertArrayEquals(expectedDDSymbols, diamondDash.getSymbols());
    }

    @Test
    void isHondaTrunkChildOfSlot() {
        HondaTrunk hondaTrunk = new HondaTrunk();

        assertInstanceOf(Slot.class, hondaTrunk);

        assertEquals(1, hondaTrunk.getMinBet());
        assertEquals(1000, hondaTrunk.getMaxBet());
        assertEquals(1.5, hondaTrunk.getReturnAmt());

        String[] expectedHTSymbols = {"🚗", "🛻", "🚕"};
        assertArrayEquals(expectedHTSymbols, hondaTrunk.getSymbols());
    }

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