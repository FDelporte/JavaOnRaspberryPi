package be.webtechie.resistors.views;

import be.webtechie.resistorcalculator.definition.ColorCode;
import be.webtechie.resistorcalculator.util.Calculate;
import be.webtechie.resistorcalculator.util.Convert;
import be.webtechie.resistorcalculator.util.ResistorValue;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class LedResistorCalculator extends VBox {

    private final Spinner<Double> inputVoltage;
    private final Spinner<Double> ledVoltage;
    private final Spinner<Double> ledAmpere;

    private final Label result;

    /**
     * Build the component.
     */
    public LedResistorCalculator() {
        setSpacing(10);

        Label title = new Label("Led resistor calculator");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        getChildren().add(title);

        HBox inputs = new HBox();
        inputs.setSpacing(10);
        inputs.setAlignment(Pos.CENTER_LEFT);
        getChildren().add(inputs);

        inputs.getChildren().add(new Label("Input voltage (V)"));
        inputVoltage = new Spinner<>(0.1, 110, 3.3);
        inputVoltage.setPrefWidth(80);
        inputVoltage.setEditable(true);
        inputVoltage.valueProperty().addListener((obs, oldValue, newValue) -> calculateValue());
        inputs.getChildren().add(inputVoltage);

        inputs.getChildren().add(new Label("Led voltage (V)"));
        ledVoltage = new Spinner<>(0.1, 110, 2.2);
        ledVoltage.setPrefWidth(80);
        ledVoltage.setEditable(true);
        ledVoltage.valueProperty().addListener((obs, oldValue, newValue) -> calculateValue());
        inputs.getChildren().add(ledVoltage);

        inputs.getChildren().add(new Label("Led ampere (A)"));
        ledAmpere = new Spinner<>(0.001, 3, 0.02);
        ledAmpere.setPrefWidth(80);
        ledAmpere.setEditable(true);
        ledAmpere.valueProperty().addListener((obs, oldValue, newValue) -> calculateValue());
        inputs.getChildren().add(ledAmpere);

        result = new Label();
        result.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");
        getChildren().add(result);

        calculateValue();
    }

    /**
     * Calculate the resistor value when one of the inputs changes
     */
    private void calculateValue() {
        try {
            long value = Calculate.resistorForLed(inputVoltage.getValue(), ledVoltage.getValue(), ledAmpere.getValue());

            result.setText("A resistor with value " + Convert.toOhmString((double) value) + " is needed");
        } catch (IllegalArgumentException ex) {
            result.setText(ex.getMessage());
        }
    }
}
