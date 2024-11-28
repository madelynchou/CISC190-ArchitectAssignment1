package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.TreasureSpins;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotTest {

    @Test
    void testGenerateSpunSymbols() {
        // Mock symbols for the test
        TreasureSpins.symbols = new String[]{"Cherry", "Lemon", "Bell"};

        String[] spunSymbols = TreasureSpins.generateSpunSymbols();

        // Verify that spunSymbols contains valid elements from symbols
        for (String symbol : spunSymbols) {
            assertTrue(java.util.Arrays.asList(TreasureSpins.symbols).contains(symbol), "Symbol should be part of the predefined symbols.");
        }

        // Verify the length of the spun symbols array
        assertEquals(TreasureSpins.symbols.length, spunSymbols.length, "Spun symbols array should have the same length as predefined symbols.");
    }

    @Test
    void testEvaluateWinCondition_FullMatch() {
        String[] spunRow = {"Cherry", "Cherry", "Cherry"};
        int result = TreasureSpins.evaluateWinCondition(spunRow);

        assertEquals(3, result, "Full match should return 3.");
    }

    @Test
    void testEvaluateWinCondition_TwoMatch() {
        String[] spunRow = {"Cherry", "Lemon", "Cherry"};
        int result = TreasureSpins.evaluateWinCondition(spunRow);

        assertEquals(2, result, "Two matches should return 2.");
    }

    @Test
    void testEvaluateWinCondition_NoMatch() {
        String[] spunRow = {"Cherry", "Lemon", "Bell"};
        int result = TreasureSpins.evaluateWinCondition(spunRow);

        assertEquals(0, result, "No match should return 0.");
    }

    @Test
    void testCalculatePayout_NoWin() {
        String[] spunRow = {"Cherry", "Lemon", "Bell"};
        int initialMoney = 100;
        int bet = 10;
        int newMoney = TreasureSpins.calculatePayout(initialMoney, spunRow, bet);

        assertEquals(90, newMoney, "Losing the bet should deduct the bet amount.");
    }

    @Test
    void testCalculatePayout_TwoMatchWin() {
        String[] spunRow = {"Cherry", "Lemon", "Cherry"};
        int initialMoney = 100;
        int bet = 10;
        TreasureSpins.returnAmt = 4; // Assume return multiplier is 4
        int newMoney = TreasureSpins.calculatePayout(initialMoney, spunRow, bet);

        assertEquals(120, newMoney, "Winning with two matches should add bet * (returnAmt / 2).");
    }

    @Test
    void testCalculatePayout_ThreeMatchWin() {
        String[] spunRow = {"Cherry", "Cherry", "Cherry"};
        int initialMoney = 100;
        int bet = 10;
        TreasureSpins.returnAmt = 4; // Assume return multiplier is 4
        int newMoney = TreasureSpins.calculatePayout(initialMoney, spunRow, bet);

        assertEquals(110, newMoney, "Winning with three matches should add the bet amount.");
    }
}