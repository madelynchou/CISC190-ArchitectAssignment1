package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.Slot;
import edu.sdccd.cisc190.players.bots.Bot;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotService implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotService.class);
    private final Bot bot;           // The bot instance this service manages
    private Slot slotMachine;        // The slot machine the bot interacts with
    private volatile boolean spinFlag = false; // Flag to indicate the bot should spin

    private static final BooleanProperty pauseFlag = new SimpleBooleanProperty(false); // Flag to pause the bot
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

    public static BooleanProperty pauseFlagProperty() {
        return pauseFlag;
    }

    // Change the slot machine this bot interacts with
    public synchronized void setSlotMachine(Slot newSlotMachine) {
        this.slotMachine = newSlotMachine;
    }

    private static final Object lock = new Object(); // Shared static lock object

    public static void pause() {
        System.out.println("DEBUG: Bots paused");
        synchronized (lock) {
            pauseFlag.set(true);
        }
    }

    public static void unpause() {
        System.out.println("DEBUG: Bots unpaused");
        synchronized (lock) {
            pauseFlag.set(false);
            lock.notifyAll(); // Notify all threads waiting on the lock
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    while (pauseFlag.get()) {
                        lock.wait(); // Wait if the thread is paused
                    }
                }

                if (spinFlag) {
                    synchronized (this) { // Ensure thread safety for instance-specific operations
                        int newBalance = slotMachine.botPlay(bot); // Simulate the spin
                        bot.setMoney(newBalance); // Update bot's balance
                        LOGGER.info("{} spun on {} and new balance: {}", bot.getName(), slotMachine.getClass().getSimpleName(), newBalance);

                        Platform.runLater(() -> {
                            // TODO: Implement GUI update logic here, e.g., updating a leaderboard or balance display
                        });

                        spinFlag = false; // Reset the spin flag
                    }
                }

                Thread.sleep(500); // Sleep for a short time to prevent busy-waiting
            } catch (InterruptedException e) {
                LOGGER.info("Thread interrupted", e);
            }
        }
    }
}