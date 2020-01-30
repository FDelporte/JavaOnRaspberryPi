package be.webtechie.pi4j.serial;

import be.webtechie.pi4j.serial.ui.MeasurementChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/SerialExample.java
 */
public class App extends Application {

    private static final String SERIAL_DEVICE = "/dev/ttyACM0";

    @Override
    public void start(Stage stage) {
        System.out.println("Starting serial communication example");

        var scene = new Scene(new MeasurementChart(SERIAL_DEVICE), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Light measurement");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
