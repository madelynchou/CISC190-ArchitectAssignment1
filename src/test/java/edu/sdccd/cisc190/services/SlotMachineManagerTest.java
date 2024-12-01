package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.*;
import edu.sdccd.cisc190.players.bots.Chase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotMachineManagerTest {
    private SlotMachineManager slotMachineManager;
    private Chase chase;
    private DiamondDash diamondDash;
    private HondaTrunk hondaTrunk;
    private MegaMoolah megaMoolah;
    private RainbowRiches rainbowRiches;
    private TreasureSpins treasureSpins;

    @BeforeEach
    void setup() {
        slotMachineManager = new SlotMachineManager();
        chase = Chase.getInstance();
        diamondDash = new DiamondDash();
        hondaTrunk = new HondaTrunk();
        megaMoolah = new MegaMoolah();
        rainbowRiches = new RainbowRiches();
        treasureSpins = new TreasureSpins();
    }

    @Test
    void testSlotMachineRotation() {
        //create slot machine list
        var slotMachines = new Slot[] {diamondDash, hondaTrunk, megaMoolah, rainbowRiches, treasureSpins};

        //Chase should initially be set to Diamond Dash
        //assertEquals(diamondDash, slotMachineManager.getBotServices().get(0).getSlotMachine());
    }
}