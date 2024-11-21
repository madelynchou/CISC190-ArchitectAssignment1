package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.Slot;
import edu.sdccd.cisc190.players.bots.Bot;
import javafx.application.Platform;

public class BotService implements Runnable {
    private final Bot bot;           // The bot instance this service manages
    private Slot slotMachine;        // The slot machine the bot interacts with
    private volatile boolean spinFlag = false; // Flag to indicate the bot should spin

    public BotService(Bot bot, Slot slotMachine) {
        this.bot = bot;
        this.slotMachine = slotMachine;
    }

    // Set the flag to trigger a spin
    public void triggerSpin() {
        spinFlag = true;
    }

    // Change the slot machine this bot interacts with
    public void setSlotMachine(Slot newSlotMachine) {
        this.slotMachine = newSlotMachine;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Check if spin is triggered
                if (spinFlag) {
                    synchronized (this) { // Ensure thread safety
                        int newBalance = slotMachine.botPlay(bot); // Simulate the spin
                        bot.setMoney(newBalance); // Update bot's balance
                        System.out.println(bot.getName() + " spun on "
                                + slotMachine.getClass().getSimpleName()
                                + " and new balance: " + newBalance);

                        // Update GUI components for this bot
                        Platform.runLater(() -> {
                            // TODO: Implement GUI update logic here, e.g., updating a leaderboard or balance display
                        });

                        spinFlag = false; // Reset the spin flag
                    }
                }

                // Sleep for a short time to prevent busy-waiting
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}