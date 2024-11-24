package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.*;
import edu.sdccd.cisc190.players.bots.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SlotMachineManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlotMachineManager.class);
    static DiamondDash diamondDash = new DiamondDash();
    static HondaTrunk hondaTrunk = new HondaTrunk();
    static MegaMoolah megaMoolah = new MegaMoolah();
    static RainbowRiches rainbowRiches = new RainbowRiches();
    static TreasureSpins treasureSpins = new TreasureSpins();

    // Flag to signal stopping all threads
    private static volatile boolean stopRequested = false;
    static List<Thread> botThreads = new ArrayList<>();
    static List<BotService> botServices = new ArrayList<>();

    public static void main() {
        System.out.println("Initializing SlotMachineManager");

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
            System.out.println("Assigned " + bot.getName() + " to " + machine.getClass().getSimpleName());

            // Periodically trigger spins for this bot
            Thread spinThread = new Thread(() -> {
                try {
                    while (!stopRequested) {
                        Thread.sleep((long) (Math.random() * 7500 + 10000)); // Random interval
                        botService.triggerSpin();
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("Thread has been interrupted", e);
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
                    rotateSlotMachines(slotMachines);
                }
            } catch (InterruptedException e) {
                LOGGER.error("Thread has been interrupted", e);
            }
        });

        rotationThread.start();
        botThreads.add(rotationThread);
    }

    // Rotate slot machines for all bots
    private static void rotateSlotMachines(List<Slot> slotMachines) {
        for (int i = 0; i < botServices.size(); i++) {
            BotService botService = botServices.get(i);
            Slot newMachine = slotMachines.get((i + 1) % slotMachines.size()); // Rotate to the next machine
            botService.setSlotMachine(newMachine);
            System.out.println("Rotated " + botService.getBot().getName() + " to " + newMachine.getClass().getSimpleName());
        }
    }

    // Method to stop all threads
    public static void stopAllThreads() {
        stopRequested = true;

        // Interrupt all bot threads
        for (Thread botThread : botThreads) {
            botThread.interrupt();
        }

        System.out.println("All threads have been stopped.");
    }
}