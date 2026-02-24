package be.webtechie.pi4j.serial.serial;

import com.pi4j.io.serial.Serial;

/**
 * Runnable to send a timestamp to the Arduino board to demonstrate the echo function.
 */
public class SerialSender implements Runnable {

    private static int INTERVAL_SEND_SECONDS = 5;

    final Serial serial;

    /**
     * Constructor which gets the serial communication object to be used to send data.
     *
     * @param serial
     */
    public SerialSender(Serial serial) {
        this.serial = serial;
    }

    @Override
    public void run() {
        // Keep looping until an error occurs
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                // Write a text to the Arduino, as demo
                this.serial.writeln("Timestamp: " + System.currentTimeMillis());

                // Wait predefined time for next loop
                Thread.sleep(INTERVAL_SEND_SECONDS * 1000);
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
                keepRunning = false;
            }
        }
    }
}