package edu.sdccd.cisc190.services;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static Stage primaryStage;
    private static final Map<String, Scene> scenes = new HashMap<>();

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void addScene(String name, Pane layout, double width, double height) {
        Scene scene = new Scene(layout, width, height);
        scenes.put(name, scene);
    }

    public static void switchScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Scene '" + name + "' not found!");
        }
    }
}