package be.webtechie.pi4j.serial.ui;

import be.webtechie.pi4j.serial.serial.SerialListener;
import be.webtechie.pi4j.serial.serial.SerialSender;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class MeasurementChart extends VBox {

    private static String SERIAL_PORT = "/dev/ttyACM0";

    private XYChart.Series<String, Integer> data;
    private SerialListener serialListener;

    public MeasurementChart() {
        // Initialize the data holder for the chart
        this.data = new XYChart.Series<>();
        this.data.setName("Value");

        // Initialize the chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Light measurement");

        lineChart.getData().add(data);

        this.getChildren().add(lineChart);

        // Create an instance of the serial communications class
        final Serial serial = SerialFactory.createInstance();
        
        // Create and register the serial data listener
        this.serialListener = new SerialListener(this.data);
        serial.addListener(this.serialListener);

        this.startSerialCommunication(serial);

        Thread t = new Thread(new SerialSender(serial));
        t.start();
    }

    private void startSerialCommunication(Serial serial) {
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
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
