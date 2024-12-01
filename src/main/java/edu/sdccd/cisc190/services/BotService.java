package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.machines.Slot;
import edu.sdccd.cisc190.players.bots.Bot;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BotService class manages the behavior of a bot interacting with a slot machine.
 * It includes functionality to pause, unpause and spin the bot on the slot machine
 * */
public class BotService implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotService.class);
    private final Bot bot;           // The bot instance this service manages
    private Slot slotMachine;        // The slot machine the bot interacts with
    private volatile boolean spinFlag = false; // Flag to indicate the bot should spin
    private static final BooleanProperty pauseFlag = new SimpleBooleanProperty(false); // Flag to pause the bot
    private static final Object lock = new Object(); // Shared static lock object

    /**
     * Constructor for BotService
     * @param bot   The bot instance managed by this service
     * @param slotMachine   The slot machine this bot interacts with
     * */
    public BotService(Bot bot, Slot slotMachine) {
        this.bot = bot;
        this.slotMachine = slotMachine;
    }

    /**
    *  Returns the bot instance managed by this service
     * @return The bot instance
    */
    public Bot getBot() {
        return bot;
    }

    /**
     * Returns the slot machine instance managed by this service
     * @return the slot machine instance
     */

    public Slot getSlotMachine() {
        return slotMachine;
    }

    /**
     * Sets the spin flag to true, triggering a spin for the bot
     * */
    public void triggerSpin() {
        spinFlag = true;
    }

    /**
     * Returns the static pause flag as a BooleanProperty
     * This property can be used for binding UI elements or observing changes
     * @return The pause flag as a BooleanProperty
     * */
    public static BooleanProperty pauseFlagProperty() {
        return pauseFlag;
    }

    /**
     * Changes the slot machine this bot interacts with
     * @param newSlotMachine The new slot machine to associate with this bot
     * */
    public synchronized void setSlotMachine(Slot newSlotMachine) {
        this.slotMachine = newSlotMachine;
    }

    /**
     * Pauses all bots by setting the pause flag to true.
     * Bots will wait until the pause flag is set to false.
     * */
    public static void pause() {
        LOGGER.debug("Bots paused");
        synchronized (lock) {
            pauseFlag.set(true);
        }
    }

    /**
     * Unpauses all bots by setting the pause flag to false.
     * Notifies all threads waiting on the pause lock to resume execution.
     * */
    public static void unpause() {
        LOGGER.debug("Bots unpaused");
        synchronized (lock) {
            pauseFlag.set(false);
            lock.notifyAll(); // Notify all threads waiting on the lock
        }
    }

    /**
     * Runs the bot service in a separate thread.
     * The bot performs spins on its slot machine when triggered, and respects the pause flag.
     * */
    @SuppressWarnings("BusyWait")
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

                        });

                        spinFlag = false; // Reset the spin flag
                    }
                }

                Thread.sleep(500); // Sleep for a short time to prevent busy-waiting
            } catch (InterruptedException e) {
                LOGGER.warn("Thread interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}