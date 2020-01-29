package be.webtechie.pi4j.serial.ui;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class MeasurementChart extends VBox {
    public MeasurementChart(XYChart.Series<String, Integer> data) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Light measurement");

        lineChart.getData().add(data);

        this.getChildren().add(lineChart);
    }
}
