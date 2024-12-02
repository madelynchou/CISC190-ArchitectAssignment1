package edu.sdccd.cisc190;

import edu.sdccd.cisc190.players.HumanPlayer;
import edu.sdccd.cisc190.services.PlayerSavesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlayerSavesServiceTest {

    private final File saveFile = new File("player_data.txt");

    @BeforeEach
    void setUp() {
        // Ensure a clean environment before each test
        if (saveFile.exists()) {
            saveFile.delete();
        }

        // Set up the HumanPlayer instance
        HumanPlayer player = HumanPlayer.getInstance();
        player.setUsername("TestUser");
        player.setMoney(100);
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test
        if (saveFile.exists()) {
            saveFile.delete();
        }
    }

    @Test
    void testSaveState() {
        // Call the saveState method
        PlayerSavesService.saveState();

        // Assert that the file is created
        assertTrue(saveFile.exists(), "Save file should be created");

        // Assert that the content of the file is correct
        try (var reader = new java.io.BufferedReader(new java.io.FileReader(saveFile))) {
            String line = reader.readLine();
            assertEquals("Username: TestUser, Money: $100", line, "Save file content should match expected format");
        } catch (Exception e) {
            fail("Unexpected exception reading the save file: " + e.getMessage());
        }
    }

    @Test
    void testLoadState() {
        // Create a save file manually
        try (var writer = new java.io.BufferedWriter(new java.io.FileWriter(saveFile))) {
            writer.write("Username: TestUser, Money: $100");
            writer.newLine();
        } catch (Exception e) {
            fail("Unexpected exception creating the save file: " + e.getMessage());
        }

        // Call the loadState method
        boolean result = PlayerSavesService.loadState();

        // Assert that the method returns true
        assertTrue(result, "loadState should return true when file exists and is valid");

        // Assert that the HumanPlayer's state is updated
        HumanPlayer player = HumanPlayer.getInstance();
        assertEquals("TestUser", player.getName(), "HumanPlayer's name should match the loaded data");
        assertEquals(100, player.getMoney(), "HumanPlayer's money should match the loaded data");
    }

    @Test
    void testLoadStateFileDoesNotExist() {
        // Ensure the save file does not exist
        if (saveFile.exists()) {
            saveFile.delete();
        }

        // Call the loadState method
        boolean result = PlayerSavesService.loadState();

        // Assert that the method returns false
        assertFalse(result, "loadState should return false when file does not exist");
    }

    @Test
    void testLoadStateInvalidData() {
        // Create a save file with invalid data
        try (var writer = new java.io.BufferedWriter(new java.io.FileWriter(saveFile))) {
            writer.write("Invalid Data");
            writer.newLine();
        } catch (Exception e) {
            fail("Unexpected exception creating the save file: " + e.getMessage());
        }

        // Call the loadState method
        boolean result = PlayerSavesService.loadState();

        // Assert that the method returns false
        assertFalse(result, "loadState should return false when file contains invalid data");
    }

    @Test
    void testDeleteState() {
        // Create a save file manually
        try (var writer = new java.io.BufferedWriter(new java.io.FileWriter(saveFile))) {
            writer.write("Username: TestUser, Money: $100");
            writer.newLine();
        } catch (Exception e) {
            fail("Unexpected exception creating the save file: " + e.getMessage());
        }

        // Call the deleteState method
        PlayerSavesService.deleteState();

        // Assert that the file is deleted
        assertFalse(saveFile.exists(), "Save file should be deleted");
    }
}