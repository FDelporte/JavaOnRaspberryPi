package be.webtechie.resistors.views;

import be.webtechie.resistorcalculator.definition.ColorCode;
import be.webtechie.resistorcalculator.util.Convert;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * Visualizes the resistor color codings in a table.
 */
public class ColorsTableView extends TableView {

    /**
     * Constructor to create the table visualization of the color codings on a resistor.
     */
    public ColorsTableView() {
        TableColumn colName = new TableColumn("Name");
        colName.setStyle("-fx-alignment: TOP-CENTER;");
        colName.setMinWidth(70);
        colName.setCellValueFactory((Callback<CellDataFeatures<ColorCode, String>, ObservableValue<String>>) p ->
                new ReadOnlyStringWrapper(p.getValue().name()));

        TableColumn colColor = new TableColumn("Color");
        colColor.setMinWidth(100);
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colColor.setCellFactory(column -> new TableCell<ColorCode, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    HBox holder = new HBox();
                    holder.setSpacing(10);
                    setGraphic(holder);

                    VBox colorBox = new VBox();
                    colorBox.setMinWidth(20);
                    colorBox.setStyle("-fx-background-color: " + Convert.toHexColorString(item) + ";");
                    holder.getChildren().add(colorBox);

                    holder.getChildren().add(new Label(Convert.toHexColorString(item)));
                }
            }
        });

        TableColumn colValue = new TableColumn("Value");
        colValue.setStyle("-fx-alignment: TOP-CENTER;");
        colValue.setMinWidth(70);
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn colMultiplier = new TableColumn("Multiplier");
        colMultiplier.setStyle("-fx-alignment: TOP-CENTER;");
        colMultiplier.setMinWidth(70);
        colMultiplier.setCellValueFactory(new PropertyValueFactory<>("multiplier"));
        colMultiplier.setCellFactory(column -> new TableCell<ColorCode, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(Convert.toOhmString(item));
                }
            }
        });

        TableColumn colTolerance = new TableColumn("Tolerance");
        colTolerance.setStyle("-fx-alignment: TOP-CENTER;");
        colTolerance.setMinWidth(70);
        colTolerance.setCellValueFactory((Callback<CellDataFeatures<ColorCode, String>, ObservableValue<String>>) p -> {
            String value = Convert.roundIfPossible(p.getValue().getTolerance());
            return new ReadOnlyStringWrapper(value.isEmpty() ? "" : value + "%");
        });

        TableColumn colTemperatureCoefficient = new TableColumn("Temp. Coeff.");
        colTemperatureCoefficient.setStyle("-fx-alignment: TOP-CENTER;");
        colTemperatureCoefficient.setMinWidth(70);
        colTemperatureCoefficient.setCellValueFactory((Callback<CellDataFeatures<ColorCode, String>, ObservableValue<String>>) p -> {
            Integer value = p.getValue().getTemperatureCoefficient();
            return new ReadOnlyStringWrapper(value == null ? "" : value + "ppm/K");
        });

        this.getColumns().addAll(colName, colColor, colValue,
                colMultiplier, colTolerance, colTemperatureCoefficient);

        ObservableList<ColorCode> data = FXCollections.observableArrayList();
        data.addAll(ColorCode.values());
        this.setItems(data);
    }
}
