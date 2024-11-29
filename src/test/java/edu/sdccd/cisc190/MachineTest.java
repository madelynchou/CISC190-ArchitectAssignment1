package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MachineTest {
    private DiamondDash diamondDash;
    private HondaTrunk hondaTrunk;
    private MegaMoolah megaMoolah;
    private RainbowRiches rainbowRiches;
    private TreasureSpins treasureSpins;

    private int bet;
    private int initialMoney;

    @BeforeEach
    void setup() {
        diamondDash = new DiamondDash();
        hondaTrunk = new HondaTrunk();
        megaMoolah = new MegaMoolah();
        rainbowRiches = new RainbowRiches();
        treasureSpins = new TreasureSpins();

        bet = 50;
        initialMoney = 100;
    }

    @Test
    void isDiamondDashChildOfSlot() {
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
        assertInstanceOf(Slot.class, hondaTrunk);

        assertEquals(1, hondaTrunk.getMinBet());
        assertEquals(1000, hondaTrunk.getMaxBet());
        assertEquals(1.5, hondaTrunk.getReturnAmt());

        String[] expectedHTSymbols = {"🚗", "🛻", "🚕"};
        assertArrayEquals(expectedHTSymbols, hondaTrunk.getSymbols());
    }

    @Test
    void isMegaMoolahChildOfSlot() {
        assertInstanceOf(Slot.class, megaMoolah);

        assertEquals(10, megaMoolah.getMinBet());
        assertEquals(1000, megaMoolah.getMaxBet());
        assertEquals(3, megaMoolah.getReturnAmt());

        String[] expectedMMSymbols = {"\uD83D\uDCB0", "\uD83E\uDD11", "\uD83D\uDCB8"};
        assertArrayEquals(expectedMMSymbols, megaMoolah.getSymbols());
    }

    @Test
    void isRainbowRichesChildOfSlot() {

        assertInstanceOf(Slot.class, rainbowRiches);

        assertEquals(25, rainbowRiches.getMinBet());
        assertEquals(1000, rainbowRiches.getMaxBet());
        assertEquals(5, rainbowRiches.getReturnAmt());

        String[] expectedRRSymbols = {"\uD83C\uDF08", "\uD83C\uDF27", "\uD83C\uDF24"};
        assertArrayEquals(expectedRRSymbols, rainbowRiches.getSymbols());
    }

    @Test
    void isTreasureSpinsChildOfSlot() {

        assertInstanceOf(Slot.class, treasureSpins);

        assertEquals(50, treasureSpins.getMinBet());
        assertEquals(1000, treasureSpins.getMaxBet());
        assertEquals(10, treasureSpins.getReturnAmt());

        String[] expectedTSSymbols = {"\uD83C\uDF53", "\uD83C\uDF4C", "\uD83C\uDF4A"};
        assertArrayEquals(expectedTSSymbols, treasureSpins.getSymbols());
    }

    @Test
    void testGenerateSpunSymbols() {
        //Using diamond dash for this test
        String[] spunSymbols = diamondDash.generateSpunSymbols();

        //test if spunSymbols has three elements, and that those elements can be found in og diamond dash
        assertEquals(3, spunSymbols.length, "The spun slot machine must have three elements.");

        for (String symbol : spunSymbols) {
            boolean isValid = Arrays.asList(diamondDash.getSymbols()).contains(symbol);
            assertTrue(isValid, "Generated symbols should be predefined in original slot game");
        }
    }

    @Test
    void testEvaluateWinCondition_FullMatch() {
        String[] fullMatchGame = {"🚗", "🚗", "🚗"};
        int result = hondaTrunk.evaluateWinCondition(fullMatchGame);
        assertEquals(3, result, "Full match should return 3.");
    }

    @Test
    void testEvaluateWinCondition_PartialMatch() {
        String[] partialMatchGame = {"🚗", "🚗", "🚕"};
        int result = hondaTrunk.evaluateWinCondition(partialMatchGame);
        assertEquals(2, result, "Partial match should return 2.");
    }

    @Test
    void testEvaluateWinCondition_NoMatch() {
        String[] noMatchGame = {"🚕", "🚗", "🛻"};
        int result = hondaTrunk.evaluateWinCondition(noMatchGame);
        assertEquals(0, result, "No match should return 0.");
    }

    @Test
    void testCalculatePayout_FullMatch() {
        //diamond dash
        String[] fullMatchDD = {"💍", "💍", "💍"};
        int newDDMoney = diamondDash.calculatePayout(initialMoney, fullMatchDD, bet);
        assertEquals(200, newDDMoney);

        //honda trunk
        String[] fullMatchHT = {"🚗", "🚗", "🚗"};
        int newHTMoney = hondaTrunk.calculatePayout(initialMoney, fullMatchHT, bet);
        assertEquals(175, newHTMoney);

        //mega moolah
        String[] fullMatchMM = {"\uD83D\uDCB0", "\uD83D\uDCB0", "\uD83D\uDCB0"};
        int newMMMoney = megaMoolah.calculatePayout(initialMoney, fullMatchMM, bet);
        assertEquals(250, newMMMoney);

        //rainbow riches
        String[] fullMatchRR = {"\uD83C\uDF08", "\uD83C\uDF08", "\uD83C\uDF08"};
        int newRRMoney = rainbowRiches.calculatePayout(initialMoney, fullMatchRR, bet);
        assertEquals(350, newRRMoney);

        //treasure spins
        String[] fullMatchTS = {"\uD83C\uDF4A", "\uD83C\uDF4A", "\uD83C\uDF4A"};
        int newTSMoney = treasureSpins.calculatePayout(initialMoney, fullMatchTS, bet);
        assertEquals(600, newTSMoney);
    }

    @Test
    void testCalculatePayout_PartialMatch() {
        //diamond dash
        String[] partialMatchDD = {"💍", "💍", "💠"};
        int newDDMoney = diamondDash.calculatePayout(initialMoney, partialMatchDD, bet);
        assertEquals(75, newDDMoney);

        //honda trunk
        String[] partialMatchHT = {"🚗", "🚗", "🚕"};
        int newHTMoney = hondaTrunk.calculatePayout(initialMoney, partialMatchHT, bet);
        assertEquals(118, newHTMoney);

        //mega moolah
        String[] partialMatchMM = {"\uD83D\uDCB0", "\uD83D\uDCB0", "\uD83E\uDD11"};
        int newMMMoney = megaMoolah.calculatePayout(initialMoney, partialMatchMM, bet);
        assertEquals(85, newMMMoney);

        //rainbow riches
        String[] partialMatchRR = {"\uD83C\uDF08", "\uD83C\uDF08","\uD83C\uDF24"};
        int newRRMoney = rainbowRiches.calculatePayout(initialMoney, partialMatchRR, bet);
        assertEquals(50, newRRMoney);

        //treasure spins
        String[] partialMatchTS = {"\uD83C\uDF4A", "\uD83C\uDF4C", "\uD83C\uDF4A"};
        int newTSMoney = treasureSpins.calculatePayout(initialMoney, partialMatchTS, bet);
        assertEquals(50, newTSMoney);
    }

    @Test
    void testCalculatePayout_NoMatch() {
        //diamond dash
        String[] noMatchDD = diamondDash.getSymbols();
        int newDDMoney = diamondDash.calculatePayout(initialMoney, noMatchDD, bet);
        assertEquals(75, newDDMoney);

        //honda trunk
        String[] noMatchHT = hondaTrunk.getSymbols();
        int newHTMoney = hondaTrunk.calculatePayout(initialMoney, noMatchHT, bet);
        assertEquals(50, newHTMoney);

        //mega moolah
        String[] noMatchMM = megaMoolah.getSymbols();
        int newMMMoney = megaMoolah.calculatePayout(initialMoney, noMatchMM, bet);
        assertEquals(85, newMMMoney);

        //rainbow riches
        String[] noMatchRR = rainbowRiches.getSymbols();
        int newRRMoney = rainbowRiches.calculatePayout(initialMoney, noMatchRR, bet);
        assertEquals(50, newRRMoney);

        //treasure spins
        String[] noMatchTS = treasureSpins.getSymbols();
        int newTSMoney = treasureSpins.calculatePayout(initialMoney, noMatchTS, bet);
        assertEquals(50, newTSMoney);
    }
}