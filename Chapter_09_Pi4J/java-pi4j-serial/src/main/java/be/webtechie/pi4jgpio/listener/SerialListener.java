package be.webtechie.pi4jgpio.listener;

import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.TimeZone;

/**
 * Listener which will print out the data received on the serial connection
 */
public class SerialListener implements SerialDataEventListener {

    private final DateFormat formatter;

    /**
     * Constructor which initializes the date formatter.
     */
    public SerialListener() {
        this.formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Called by Serial when new data is received.
     */
    @Override
    public void dataReceived(SerialDataEvent event) {
        try {
            String received = event.getAsciiString()
                .replace("\t", "")
                .replace("\n", "");
            System.out.println(formatter.format(LocalTime.now())
                    + " - Received: " + received);
        } catch (IOException ex) {
            System.err.println("Serial error: " + ex.getMessage());
        }
    }
}
