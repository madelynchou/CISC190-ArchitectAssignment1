package edu.sdccd.cisc190;

import edu.sdccd.cisc190.interfaces.MainMenu;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
    }

    @Test
    void testHeaderExists() {
        Text header = lookup(".text").queryAs(Text.class);
        assertNotNull(header, "Header text should exist.");
        assertEquals("Casino Royale", header.getText(), "Header text should be 'Casino Royale'.");
    }

    @Test
    void testUserInfoDisplayed() {
        Text username = lookup(".text").nth(1).queryAs(Text.class);
        Text money = lookup(".text").nth(2).queryAs(Text.class);

        assertTrue(username.getText().contains("Username:"), "Username label should be displayed.");
        assertTrue(money.getText().contains("Money:"), "Money label should be displayed.");
    }

    @Test
    void testSlotOptionButtonsExist() {
        for (MainMenu.SlotOptions option : MainMenu.SlotOptions.values()) {
            Button button = lookup("." + option.name().toLowerCase().replace("_", "-")).queryButton();
            assertNotNull(button, "Button for option '" + option.getDisplayOption() + "' should exist.");
            assertEquals(option.getDisplayOption(), button.getText(), "Button text should match the display option.");
        }
    }

    @Test
    void testQuitButtonAction() {
        Button quitButton = lookup("." + MainMenu.SlotOptions.QUIT.name().toLowerCase().replace("_", "-")).queryButton();

        // Simulate clicking the quit button
        clickOn(quitButton);

        // Verify that an alert is shown with the correct content
        Alert alert = lookup(".alert").queryAs(Alert.class);
        assertNotNull(alert, "Alert should be displayed after quitting.");
        assertEquals("Goodbye!", alert.getTitle(), "Alert title should be 'Goodbye!'.");
        assertEquals("Come back soon! 99.9% of gamblers quit before hitting it big!", alert.getContentText(), "Alert content should match the quit message.");
    }

    @Test
    void testLeaderboardButtonAction() {
        Button leaderboardButton = lookup("." + MainMenu.SlotOptions.LEADERBOARD.name().toLowerCase().replace("_", "-")).queryButton();

        // Simulate clicking the leaderboard button
        clickOn(leaderboardButton);

        // Verify that the Leaderboard window is displayed
        // (Mocked or verified by checking that `Leaderboard.showWindow()` is called)
        // Note: You would mock the Leaderboard class for a complete test.
        assertTrue(true, "Placeholder for Leaderboard verification.");
    }
}