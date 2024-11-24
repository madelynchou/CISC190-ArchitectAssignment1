package edu.sdccd.cisc190.services;

import edu.sdccd.cisc190.players.HumanPlayer;

import java.io.*;

public class PlayerSavesService {
    public static void saveState() {
        HumanPlayer player = HumanPlayer.getInstance();
        String data = "Username: " + player.getUsername() + ", Money: $" + player.getMoney();

        try {
            // Delete the file if it exists
            File file = new File("player_data.txt");
            if (file.exists()) {
                if (!file.delete()) {
                    System.err.println("Failed to delete existing player_data.txt file.");
                    return;
                }
            }

            // Write new data to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(data);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }

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
                    System.err.println("Error reading player data: " + e.getMessage());
                }
            }
            return false; // File does not exist or data could not be loaded
    }

    public static void deleteState() {
        File file = new File("player_data.txt");
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("Failed to delete existing player_data.txt file.");
            }
        }
    }
}
