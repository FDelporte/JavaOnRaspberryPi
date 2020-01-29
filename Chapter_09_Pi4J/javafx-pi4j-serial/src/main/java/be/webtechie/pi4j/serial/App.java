package be.webtechie.pi4j.serial;

import be.webtechie.pi4j.serial.listener.SerialListener;
import be.webtechie.pi4j.serial.ui.MeasurementChart;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.StopBits;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/SerialExample.java
 */
public class App extends Application {

    private static String SERIAL_PORT = "/dev/ttyACM0";
    private static int INTERVAL_SEND_SECONDS = 5;

    private XYChart.Series<String, Integer> data;
    private SerialListener serialListener;

    @Override
    public void start(Stage stage) {
        System.out.println("Starting serial communication example");

        // Initialize the data holder for the chart
        this.data = new XYChart.Series<>();
        this.data.setName("Value");

        // Initialize the chart UI
        MeasurementChart chart = new MeasurementChart(this.data);

        // Open the JavaFX UI
        var scene = new Scene(chart, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Light measurement");
        stage.show();

        // Create an instance of the serial communications class
        final Serial serial = SerialFactory.createInstance();

        // Create and register the serial data listener
        this.serialListener = new SerialListener(this.data);
        serial.addListener(this.serialListener);

        try {
            // Create serial config object
            SerialConfig config = new SerialConfig();
            config.device(SERIAL_PORT)
                    .baud(Baud._38400)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);

            // Display connection details
            System.out.println("Connection: " + config.toString());

            // Open the serial port with the configuration
            serial.open(config);

            // Keep looping until error occurs
            boolean keepRunning = true;
            while (keepRunning) {
                try {
                    // Write a text to the Arduino, as demo
                    serial.writeln("Timestamp: " + System.currentTimeMillis());
                } catch (IllegalStateException ex) {
                    System.err.println("Error: " + ex.getMessage());
                    keepRunning = false;
                }

                // Wait predefined time for next loop
                Thread.sleep(INTERVAL_SEND_SECONDS * 1000);
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
