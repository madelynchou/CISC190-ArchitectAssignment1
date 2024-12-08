package edu.sdccd.cisc190;

import edu.sdccd.cisc190.views.SetupView;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetupViewTest {

    @Test
    void testWindowTitleMatchesGame() throws Exception {
        //initialize JavaFX runtime
        Platform.startup(() -> {

        });

        Platform.runLater(() -> {
            try {
                SetupView setupView = new SetupView();
                Stage testStage = new Stage();

                setupView.start(testStage);
                assertEquals("Casino Royale - Sign In", testStage.getTitle());
            } finally {
                Platform.exit();
            }
        });
    }
}