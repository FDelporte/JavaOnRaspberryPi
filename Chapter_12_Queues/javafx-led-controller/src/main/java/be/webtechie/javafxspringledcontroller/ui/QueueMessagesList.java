package be.webtechie.javafxspringledcontroller.ui;

import be.webtechie.javafxspringledcontroller.client.ReceivedMessage;
import be.webtechie.javafxspringledcontroller.event.EventListener;
import be.webtechie.javafxspringledcontroller.led.LedCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class QueueMessagesList extends TableView implements EventListener {

    private ObservableList<ReceivedMessage> list = FXCollections.observableArrayList();

    /**
     * Construct the UI as a {@link TableView}.
     */
    QueueMessagesList() {
        TableColumn colTimestamp = new TableColumn("Timestamp");
        colTimestamp.setStyle("-fx-alignment: TOP-LEFT;");
        colTimestamp.setMinWidth(150);
        colTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        TableColumn colCommand = new TableColumn("Command");
        colCommand.setStyle("-fx-alignment: TOP-LEFT;");
        colCommand.setMinWidth(100);
        colCommand.setCellValueFactory(new PropertyValueFactory<>("ledCommand"));
        colCommand.setCellFactory(column -> new TableCell<LedCommand, LedCommand>() {
            @Override
            protected void updateItem(LedCommand item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getLedEffect().name());
                }
            }
        });

        TableColumn colSpeed = new TableColumn("Speed");
        colSpeed.setStyle("-fx-alignment: TOP-LEFT;");
        colSpeed.setMinWidth(70);
        colSpeed.setCellValueFactory(new PropertyValueFactory<>("ledCommand"));
        colSpeed.setCellFactory(column -> new TableCell<LedCommand, LedCommand>() {
            @Override
            protected void updateItem(LedCommand item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item.getSpeed()));
                }
            }
        });

        TableColumn colColor1 = new TableColumn("Color 1");
        colColor1.setStyle("-fx-alignment: TOP-CENTER;");
        colColor1.setMinWidth(70);
        colColor1.setCellValueFactory(new PropertyValueFactory<>("ledCommand"));
        colColor1.setCellFactory(column -> new TableCell<LedCommand, LedCommand>() {
            @Override
            protected void updateItem(LedCommand item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getColor1().toString());
                    setStyle("-fx-background-color: " + item.getColor1().toString().replace("0x", "#"));
                }
            }
        });

        TableColumn colColor2 = new TableColumn("Color 2");
        colColor2.setStyle("-fx-alignment: TOP-CENTER;");
        colColor2.setMinWidth(70);
        colColor2.setCellValueFactory(new PropertyValueFactory<>("ledCommand"));
        colColor2.setCellFactory(column -> new TableCell<LedCommand, LedCommand>() {
            @Override
            protected void updateItem(LedCommand item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getColor2().toString());
                    setStyle("-fx-background-color: " + item.getColor2().toString().replace("0x", "#"));
                }
            }
        });

        TableColumn colData = new TableColumn("Data");
        colData.setStyle("-fx-alignment: TOP-CENTER;");
        colData.setMinWidth(150);
        colData.setCellValueFactory(new PropertyValueFactory<>("ledCommand"));
        colData.setCellFactory(column -> new TableCell<LedCommand, LedCommand>() {
            @Override
            protected void updateItem(LedCommand item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.toCommandString());
                }
            }
        });

        this.getColumns().addAll(
                colTimestamp,
                colCommand,
                colSpeed,
                colColor1,
                colColor2,
                colData);

        this.setItems(this.list);
    }

    /**
     * {@link LedCommand} received from Mosquitto.
     * We put it on top of our internal list so it gets added to the table.
     *
     * @param ledCommand The {@link LedCommand}
     */
    @Override
    public void onQueueMessage(LedCommand ledCommand) {
        this.list.add(0, new ReceivedMessage(ledCommand));
    }
}
