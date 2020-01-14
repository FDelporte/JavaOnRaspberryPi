package be.webtechie.pi4jgpio.listener;

import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import java.io.IOException;

/**
 * Listener which will print out the data received on the serial connection
 */
public class SerialListener implements SerialDataEventListener {
    @Override
    public void dataReceived(SerialDataEvent event) {
        // NOTE! - It is extremely important to read the data received from the
        // serial port.  If it does not get read from the receive buffer, the
        // buffer will continue to grow and consume memory.

        // Print out the data received to the console
        try {
            System.out.println("[HEX DATA]   " + event.getHexByteString());
            System.out.println("[ASCII DATA] " + event.getAsciiString());
        } catch (IOException ex) {
            System.err.println("Serial error: " + ex.getMessage());
        }
    }
}
