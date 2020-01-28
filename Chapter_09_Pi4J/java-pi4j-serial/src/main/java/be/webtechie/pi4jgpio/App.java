package be.webtechie.pi4jgpio;

import be.webtechie.pi4jgpio.listener.SerialListener;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPort;
import com.pi4j.io.serial.StopBits;
import java.io.IOException;
import java.util.Date;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/SerialExample.java
 */
public class App {

    private static String SERIAL_PORT = "/dev/ttyACM0";
    private static int INTERVAL_SEND_SECONDS = 5;

    public static void main( String[] args ) {
        System.out.println("Starting serial communication example");

        // Create an instance of the serial communications class
        final Serial serial = SerialFactory.createInstance();

        // Create and register the serial data listener
        serial.addListener(new SerialListener());

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

            // Keep looping until error occures
            boolean keepRunning = true;
            while (keepRunning) {
                try {
                    // Write a text to the Arduino, as demo
                    serial.writeln("Timestamp: " + System.currentTimeMillis());
                } catch(IllegalStateException ex){
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
}
