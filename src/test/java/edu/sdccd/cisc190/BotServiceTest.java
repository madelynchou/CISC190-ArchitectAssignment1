package edu.sdccd.cisc190;

import edu.sdccd.cisc190.machines.DiamondDash;
import edu.sdccd.cisc190.players.bots.Chase;
import edu.sdccd.cisc190.services.BotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotServiceTest {
    private DiamondDash diamondDash;
    private BotService botService;
    private Chase chase;

    @BeforeEach
    void setup() {
        chase = Chase.getInstance();
        diamondDash = new DiamondDash();
        botService = new BotService(chase, diamondDash);
    }

    @Test
    void testBotAssignmentToMachine() {
        Thread botThread = new Thread(botService);
        botThread.start();

        assertEquals(diamondDash, botService.getSlotMachine(), "Chase should be assigned to Diamond Dash.");
    }
}