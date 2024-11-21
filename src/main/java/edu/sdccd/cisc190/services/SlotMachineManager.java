package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.*;
import edu.sdccd.cisc190.players.bots.*;

import java.util.ArrayList;
import java.util.List;

public class SlotMachineManager {
    static DiamondDash diamondDash = new DiamondDash();
    static HondaTrunk hondaTrunk = new HondaTrunk();
    static MegaMoolah megaMoolah = new MegaMoolah();
    static RainbowRiches rainbowRiches = new RainbowRiches();
    static TreasureSpins treasureSpins = new TreasureSpins();

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

        // List to store services
        List<Thread> botThreads = new ArrayList<>();

        // Start a thread for each bot
        for (Bot bot : bots) {
            // Assign the bot to a random slot machine
            Slot machine = assignRandomMachine();

            // Create a new BotService for the bot and machine
            BotService botService = new BotService(bot, machine);

            // Wrap botService in a thread and start it
            Thread botThread = new Thread(botService);
            botThread.start();
            botThreads.add(botThread);

            // Log the bot's assignment
            System.out.println("Assigned " + bot.getName() + " to " + machine.getClass().getSimpleName());

            // Periodically trigger spins for this bot
            Thread spinThread = new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep((long) (Math.random() * 5000 + 2000)); // Random interval
                        botService.triggerSpin();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            spinThread.start();
            botThreads.add(spinThread);
        }
    }

    private static Slot assignRandomMachine() {
        int randomMachine = (int) (Math.random() * 5); // Generate a random number between 0 and 4
        switch (randomMachine) {
            case 0: return diamondDash;
            case 1: return hondaTrunk;
            case 2: return megaMoolah;
            case 3: return rainbowRiches;
            case 4: return treasureSpins;
            default: throw new IllegalStateException("Unexpected value: " + randomMachine);
        }
    }
}