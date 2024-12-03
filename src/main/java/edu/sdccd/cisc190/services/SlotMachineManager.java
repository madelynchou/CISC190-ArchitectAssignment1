package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.*;
import edu.sdccd.cisc190.players.bots.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages slot machines and bots interacting with them.
 * Handles the creation, assignment, rotation and control of bot threads interacting with different slot machines
 * */
public class SlotMachineManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlotMachineManager.class);

    // Instances of slot machines
    static DiamondDash diamondDash = new DiamondDash();
    static HondaTrunk hondaTrunk = new HondaTrunk();
    static MegaMoolah megaMoolah = new MegaMoolah();
    static RainbowRiches rainbowRiches = new RainbowRiches();
    static TreasureSpins treasureSpins = new TreasureSpins();

    // Lists to manage bot threads and services
    private static volatile boolean stopRequested = false;
    public static List<Thread> botThreads = new ArrayList<>();
    public static List<BotService> botServices = new ArrayList<>();

    /**
     * Getter that to obtain the boolean value of stopRequested
     * @return the value of stopRequested (true or false)
     */

    public static boolean getStopRequested() {
        return stopRequested;
    }

    /**
     * Main method to initialize and manage bot services and slot machines
     * Assigns bots to slot machines, starts their threads, and manages periodic tasks
     * */
    public static void main() {
        LOGGER.info("Initializing SlotMachineManager");

        // List of bots
        List<Bot> bots = List.of(
                Chase.getInstance(),
                HondaBoyz.getInstance(),
                MrBrooks.getInstance(),
                ProfessorHuang.getInstance(),
                AnitaMaxWynn.getInstance()
        );

        // List of slot machines
        List<Slot> slotMachines = List.of(diamondDash, hondaTrunk, megaMoolah, rainbowRiches, treasureSpins);

        // Start a service for each bot
        for (int i = 0; i < bots.size(); i++) {
            Bot bot = bots.get(i);
            Slot machine = slotMachines.get(i % slotMachines.size()); // Assign initial machine
            BotService botService = new BotService(bot, machine);

            // Wrap botService in a thread and start it
            Thread botThread = new Thread(botService);
            botThread.start();
            botThreads.add(botThread);
            botServices.add(botService);

            // Log the bot's assignment
            LOGGER.debug("Assigned {} to {}", bot.getName(), machine.getClass().getSimpleName());

            // Periodically trigger spins for this bot
            Thread spinThread = new Thread(() -> {
                try {
                    while (!stopRequested) {
                        Thread.sleep((long) (Math.random() * 7500 + 10000)); // Random interval
                        if (stopRequested) break;
                        botService.triggerSpin();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.info("Thread has been interrupted", e);
                }
            });

            spinThread.start();
            botThreads.add(spinThread);
        }

        // Start a thread to rotate machines
        Thread rotationThread = new Thread(() -> {
            try {
                while (!stopRequested) {
                    Thread.sleep(15000); // Rotate machines every 15 seconds
                    if (stopRequested) break;
                    rotateSlotMachines(slotMachines);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.info("Thread has been interrupted", e);
            }
        });

        rotationThread.start();
        botThreads.add(rotationThread);
    }

    /**
     * Rotates the slot machines assigned to bots.
     * Each bot is moved to the next slot machine in the list.
     * @param slotMachines The list of available slot machines
     * */
    private static void rotateSlotMachines(List<Slot> slotMachines) {
        for (int i = 0; i < botServices.size(); i++) {
            BotService botService = botServices.get(i);
            Slot newMachine = slotMachines.get((i + 1) % slotMachines.size()); // Rotate to the next machine
            botService.setSlotMachine(newMachine);
            LOGGER.info("Rotated {} to {}", botService.getBot().getName(), newMachine.getClass().getSimpleName());
        }
    }

    /**
     * Stops all threads managed by this class.
     * Signals threads to stop and interrupts them to end execution
     * */
    public static void stopAllThreads() {
        stopRequested = true;

        // Interrupt all bot threads
        for (Thread botThread : botThreads) {
            if (botThread.isAlive()) {
                try {
                    botThread.interrupt();
                    botThread.join(1000); //wait for threads to finish
                } catch (InterruptedException e) {
                    LOGGER.warn("Failed to stop thread: {}", botThread.getName(), e);
                }
            }
        }

        LOGGER.info("All threads have been stopped.");
    }

    /**
     * Resets all threads to og state
     * used for junit testing
     */
    public static void reset() {
        stopRequested = false;
        botThreads.clear();
        botServices.clear();
    }
}