package be.webtechie.pi4j.serial.serial;

import be.webtechie.pi4j.serial.data.ArduinoMessage;
import be.webtechie.pi4j.serial.data.ArduinoMessageMapper;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;

/**
 * Listener which will print out the data received on the serial connection
 */
public class SerialListener implements SerialDataEventListener {

    private final DateTimeFormatter formatter;
    private final XYChart.Series<String, Integer> data;

    /**
     * Constructor which initializes the date formatter.
     *
     * @param data The data series to which the light values must be added
     */
    public SerialListener(XYChart.Series<String, Integer> data) {
        this.data = data;
        this.formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
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

            ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(received);
            String timestamp = LocalTime.now().format(formatter);

            if (arduinoMessage.type.equals("light")) {
                // We need to use the runLater approach as this data is handled
                // in another thread as the UI-component
                Platform.runLater(() -> {
                    data.getData().add(new XYChart.Data(timestamp, arduinoMessage.getIntValue()));
                });
            }

            System.out.println(timestamp + " - Received: " + received);
        } catch (IOException ex) {
            System.err.println("Serial error: " + ex.getMessage());
        }
    }
}
