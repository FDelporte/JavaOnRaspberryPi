package be.webtechie.pinninginfo.views;

import be.webtechie.piheaders.definition.HeaderPins;
import be.webtechie.piheaders.definition.PinType;
import be.webtechie.piheaders.pin.HeaderPin;
import be.webtechie.pinninginfo.util.Converter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Visualizes the header in a table.
 */
public class HeaderTableView extends TableView {

    private ObservableList<HeaderPin> data = FXCollections.observableArrayList();

    /**
     * Constructor to create the table visualization of the header.
     *
     * @param headerPins {@link HeaderPins} to be visualized.
     */
    public HeaderTableView(HeaderPins headerPins) {
        this.setMinWidth(1000);
        this.setMinHeight(1000);

        TableColumn colPinNumber = new TableColumn("Pin n°");
        colPinNumber.setStyle("-fx-alignment: TOP-CENTER;");
        colPinNumber.setMinWidth(70);
        colPinNumber.setCellValueFactory(new PropertyValueFactory<>("pinNumber"));

        TableColumn colWiringPiNumber = new TableColumn("WiringPi n°");
        colWiringPiNumber.setStyle("-fx-alignment: TOP-CENTER;");
        colWiringPiNumber.setMinWidth(70);
        colWiringPiNumber.setCellValueFactory(new PropertyValueFactory<>("wiringPiNumber"));

        TableColumn colBcmNumber = new TableColumn("BCM n°");
        colBcmNumber.setStyle("-fx-alignment: TOP-CENTER;");
        colBcmNumber.setMinWidth(70);
        colBcmNumber.setCellValueFactory(new PropertyValueFactory<>("bcmNumber"));

        TableColumn colName = new TableColumn("Name");
        colName.setMinWidth(125);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn colType = new TableColumn("Type");
        colType.setMinWidth(150);
        colType.setCellValueFactory(new PropertyValueFactory<>("pinType"));
        colType.setCellFactory(column -> new TableCell<PinType, PinType>() {
            @Override
            protected void updateItem(PinType item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.name());
                    setStyle("-fx-font-size: 9px; "
                            + "-fx-padding: 1; "
                            + "-fx-border-style: solid inside; "
                            + "-fx-border-width: 2; "
                            + "-fx-border-insets: 1; "
                            + "-fx-border-color: " + Converter.intToColor(item.getColor()).toString().replace("0x", "#") + ";");
                }
            }
        });

        TableColumn colFunction = new TableColumn("Function");
        colFunction.setMinWidth(70);
        colFunction.setCellValueFactory(new PropertyValueFactory<>("pinFunction"));

        TableColumn colRemark = new TableColumn("Remark");
        colRemark.setMinWidth(550);
        colRemark.setCellValueFactory(new PropertyValueFactory<>("remark"));

        this.getColumns().addAll(colPinNumber,
                colWiringPiNumber,
                colBcmNumber,
                colName,
                colType,
                colFunction,
                colRemark);

        this.data.addAll(headerPins.getPins());
        this.setItems(data);
    }
}
