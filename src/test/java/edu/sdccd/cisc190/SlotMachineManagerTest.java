package edu.sdccd.cisc190;

import edu.sdccd.cisc190.services.SlotMachineManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class SlotMachineManagerTest {
    @BeforeEach
    void setup() {
        //manager starts with a clean slate before each test
        SlotMachineManager.reset();
    }

    @AfterEach
    void tearDown() {
        //clean up after each test
        SlotMachineManager.stopAllThreads();
    }

    @Test
    void testGetStopRequested() {
        assertFalse(SlotMachineManager.getStopRequested(), "stopRequested should initially be set to false");
        SlotMachineManager.stopAllThreads();
        assertTrue(SlotMachineManager.getStopRequested(), "stopRequested should be set to true after calling stopAllThreads()");
    }

    @Test
    void testMain() {
        //initialize main() method of SlotMachineManager
        SlotMachineManager.main();

        //verify that the bot threads and bot services are initialized
        assertFalse(SlotMachineManager.botThreads.isEmpty(), "Bot threads should be initialized");
        assertFalse(SlotMachineManager.botServices.isEmpty(), "Bot services should be initialized");

        //ensure that the size of bot services and threads equals to number of bots
        int numOfBots = 5;
        assertEquals(numOfBots, SlotMachineManager.botServices.size(), "Number of services should equal number of bots");
    }

    @Test
    void testStopAllThreads() throws InterruptedException {

    }
}