package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.DiamondDash;
import edu.sdccd.cisc190.machines.HondaTrunk;
import edu.sdccd.cisc190.players.bots.Chase;
import edu.sdccd.cisc190.services.BotService;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotServiceTest {
    private DiamondDash diamondDash;
    private HondaTrunk hondaTrunk;
    private BotService botService;
    private Chase chase;

    @BeforeAll
    static void initializeJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setup() {
        chase = Chase.getInstance();
        diamondDash = new DiamondDash();
        hondaTrunk = new HondaTrunk();
        botService = new BotService(chase, diamondDash);
        Thread botThread = new Thread(botService);
        botThread.start();
    }

    @Test
    void testBotAssignmentToMachine() {
        //Chase bot should be assigned to Diamond Dash slot machine
        assertEquals(diamondDash, botService.getSlotMachine(), "Chase should be assigned to Diamond Dash.");

        //Reassign Chase to Honda Trunk and verify bot was properly reassigned
        botService.setSlotMachine(hondaTrunk);
        assertEquals(hondaTrunk, botService.getSlotMachine(), "Chase should be set to Honda Trunk");
    }

    @Test
    void testTriggerSpinUpdatesMoney() throws InterruptedException {
        //store bot's initial money
        int initialMoney = chase.getMoney();

        //trigger spin
        botService.triggerSpin();

        //wait a second for spin to complete
        Thread.sleep(2000);

        //ensure bot money updates
        int updatedMoney = chase.getMoney();
        assertNotEquals(initialMoney, updatedMoney, "Bot's money should update after spin.");
    }

}