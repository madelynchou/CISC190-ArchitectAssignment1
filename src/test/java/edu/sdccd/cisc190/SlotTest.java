package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.*;
import edu.sdccd.cisc190.players.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SlotTest {
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

    @Test
    void testCanBetWithVariousBets() {
        //create a new humanPlayer and set its money to 1000
        HumanPlayer humanPlayer = HumanPlayer.getInstance();
        humanPlayer.setMoney(1000);

        //Place a valid bet within range of machine max and min bet
        int betWithinRange = 100;
        assertTrue(diamondDash.canBet(betWithinRange), "A player should be able to bet $100 on Diamond Dash");

        //Place an invalid bet less than minBet of slot machine
        int betTooLow = 10;
        assertFalse(diamondDash.canBet(betTooLow), "A player cannot bet $10 on Diamond Dash! Bet is too low");

        //Place an invalid bet greater than maxBet of slot machine
        int betTooHigh = 2000;
        assertFalse(diamondDash.canBet(betTooHigh), "A player cannot bet $2000 on Diamond Dash! Bet is too high");

        //Place a valid bet exactly equal to minBet
        int betMin = diamondDash.getMinBet();
        assertTrue(diamondDash.canBet(betMin), "A player should be able to bet the min bet on Diamond Dash");

        //Place a valid bet exactly equal to maxBet
        int betMax = diamondDash.getMaxBet();
        assertTrue(diamondDash.canBet(betMax), "A player should be able to bet the max bet on Diamond Dash");

        //Player cannot bet more than what they have
        int notEnoughMoney = 1200;
        assertFalse(diamondDash.canBet(notEnoughMoney), "A player cannot bet more than what they have");
    }
}