package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

//    public static final String APP_NAME_FILE = "AppName.txt";
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public static String getAppName() throws IOException {
//        String appName;
//        try (InputStream is = Main.class.getClassLoader().getResourceAsStream(APP_NAME_FILE)) {
//            if(is == null) throw new IOException(APP_NAME_FILE + " could not be found!");
//            appName = new BufferedReader(new InputStreamReader(is)).readLine();
//        }
//
//        return appName;
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Label label = new Label("The content inside the TitledPane");
//        TitledPane titledPane = new TitledPane(getAppName(), label);
//        titledPane.setCollapsible(false);
//
//        titledPane.setExpanded(true);
//        titledPane.setExpanded(false);
//
//        Scene scene = new Scene(new VBox(titledPane));
//        stage.setScene(scene);
//
//        stage.show();
//    }
}
