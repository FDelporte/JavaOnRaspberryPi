package be.webtechie.javafxmosquitto.ui;

import be.webtechie.javafxmosquitto.client.QueueClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QueueMonitor extends HBox {

    private QueueClient queueClient;

    private TextField textInput;

    public QueueMonitor(final QueueClient queueClient) {
        this.queueClient = queueClient;

        this.setSpacing(25);

        ListView<String> list = new ListView<>();
        list.setItems(queueClient.getQueueItems());
        this.getChildren().add(list);

        // Vertical list with label, input and button
        VBox inputHolder = new VBox();
        this.getChildren().add(inputHolder);

        Label label = new Label("Send message to queueu");
        inputHolder.getChildren().add(label);

        this.textInput = new TextField();
        inputHolder.getChildren().add(this.textInput);

        Button sendTimestamp = new Button("Send");
        sendTimestamp.setOnAction(this::sendToQueue);
        inputHolder.getChildren().add(sendTimestamp);
    }

    private void sendToQueue(ActionEvent e) {
        Platform.runLater(() -> {
            this.queueClient.sendMessage("testing/TestTopic", this.textInput.getText());
        });
    }
}
