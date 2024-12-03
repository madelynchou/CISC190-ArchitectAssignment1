package edu.sdccd.cisc190;

import edu.sdccd.cisc190.views.MainMenuView;
import edu.sdccd.cisc190.views.SlotMachineView;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class SlotMachineViewTest extends ApplicationTest {

    private HBox slotsRow;

    @Override
    public void start(Stage stage) {
        //set up SlotMachineView
        SlotMachineView.showWindow(stage, 10, MainMenuView.SlotOptions.DIAMOND_DASH);

        //after window is displayed, access VBox and HBox
        Platform.runLater(() -> {
            VBox layout = (VBox) stage.getScene().getRoot();
            slotsRow = (HBox) layout.getChildren().get(4);
        });
    }

    @Test
    public void testSlotMachinesPaneDisplayed() {

        //make sure layout is loaded fully
        Platform.runLater(() -> {
            //verify that symbols are part of HBox
            assert slotsRow != null;
            assertEquals(3, slotsRow.getChildren().size(), "Slot machine display should contain 3 slots");
        });
    }
}