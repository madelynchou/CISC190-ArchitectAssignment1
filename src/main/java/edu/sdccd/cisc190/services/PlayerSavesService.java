package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.players.HumanPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

@SuppressWarnings("LoggingSimilarMessage")
public class PlayerSavesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerSavesService.class);

    /*
     * Saves the user's name and money into a player_data.txt file on quit to persist their progress
     * */
    public static void saveState() {
        HumanPlayer player = HumanPlayer.getInstance();
        String data = "Username: " + player.getName() + ", Money: $" + player.getMoney();

        try {
            // Delete the file if it exists
            File file = new File("player_data.txt");
            if (file.exists()) {
                if (!file.delete()) {
                    LOGGER.error("Failed to delete existing player_data.txt file.");
                    return;
                }
            }

            // Write new data to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(data);
                writer.newLine();
            }

        } catch (IOException e) {
            LOGGER.error("Error saving player data.", e);
        }
    }

    /*
     * Loads user data from player_data.txt file if available on game open
     * */
    public static boolean loadState() {
        File file = new File("player_data.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] data = line.split(", ");
                    String username = data[0].split(": ")[1];
                    int money = Integer.parseInt(data[1].split(": ")[1].replace("$", ""));

                    HumanPlayer player = HumanPlayer.getInstance();
                    player.setUsername(username);
                    player.setMoney(money);

                    return true; // Data successfully loaded
                }
            } catch (IOException | NumberFormatException e) {
                LOGGER.error("Error reading player data", e);
            }
        }
        return false; // File does not exist or data could not be loaded
    }

    /*
     * Deletes user's information in player_data.txt if available
     * */
    public static void deleteState() {
        File file = new File("player_data.txt");
        if (file.exists()) {
            if (!file.delete()) {
                LOGGER.error("Failed to delete existing player_data.txt file.");
            }
        }
    }
}