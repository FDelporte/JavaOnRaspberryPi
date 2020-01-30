package be.webtechie.pi4j.serial;

import be.webtechie.pi4j.serial.ui.MeasurementChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/SerialExample.java
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        System.out.println("Starting serial communication example");

        // Open the JavaFX UI
        var scene = new Scene(new MeasurementChart(), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Light measurement");
        stage.show();

        
    }

    public static void main(String[] args) {
        launch();
    }
}
