package be.webtechie.javafxspringledcontroller.ui;

import be.webtechie.javafxspringledcontroller.client.ReceivedMessage;
import be.webtechie.javafxspringledcontroller.event.EventListener;
import be.webtechie.javafxspringledcontroller.led.LedCommand;
import be.webtechie.javafxspringledcontroller.ui.tablecell.ColorView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class QueueMessagesList extends TableView implements EventListener {

    private ObservableList<ReceivedMessage> queueItems = FXCollections.observableArrayList();

    public QueueMessagesList() {
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

        this.setItems(this.queueItems);

        // Test date
        this.onQueueMessage("2:50:255:0:0:0:255:0");
    }

    @Override
    public void onQueueMessage(String message) {
        this.queueItems.add(
                new ReceivedMessage(
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        new LedCommand(message)
                )
        );
    }
}
