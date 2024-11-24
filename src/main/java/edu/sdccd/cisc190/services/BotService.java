package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.Slot;
import edu.sdccd.cisc190.players.bots.Bot;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotService implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotService.class);
    private final Bot bot;           // The bot instance this service manages
    private Slot slotMachine;        // The slot machine the bot interacts with
    private volatile boolean spinFlag = false; // Flag to indicate the bot should spin

    public BotService(Bot bot, Slot slotMachine) {
        this.bot = bot;
        this.slotMachine = slotMachine;
    }

    public Bot getBot() {
        return bot;
    }

    // Set the flag to trigger a spin
    public void triggerSpin() {
        spinFlag = true;
    }

    // Change the slot machine this bot interacts with
    public synchronized void setSlotMachine(Slot newSlotMachine) {
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
                        LOGGER.debug("{} spun on {} and new balance: {}", bot.getName(), slotMachine.getClass().getSimpleName(), newBalance);

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
                LOGGER.info("Thread interrupted", e);
            }
        }
    }
}