package be.webtechie.javafxmosquitto.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class QueueClient {

    final static String PI_ADDRESS = "192.168.0.213";

    private MqttClient client;

    private ObservableList<String> queueItems = FXCollections.observableArrayList();

    public QueueClient() {
        this.initConnection();

        if (!this.initConnection()) {
            System.err.println("Initializing connection failed");
            return;
        }

        this.connect();
        this.subscribe();
    }

    private boolean initConnection() {
        try {
            this.client = new MqttClient("tcp://" + PI_ADDRESS + ":1883", MqttClient.generateClientId());
            return true;
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }

        return false;
    }

    private void connect() {
        try {
            this.client.connect();
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }
    }

    private void subscribe() {
        try {
            this.client.setCallback(new ClientCallback(this.queueItems));
            this.client.subscribe("testing/TestTopic");
        } catch (MqttException ex) {
            System.err.println("Error while subscribing: " + ex.getMessage());
        }
    }

    public void sendMessage(String topic, String messageText) {
        if (!this.client.isConnected()) {
            System.err.println("The queue client is not connected!");
        }

        MqttMessage message = new MqttMessage();
        message.setPayload(messageText.getBytes());

        try {
            this.client.publish(topic, message);
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }
    }

    public ObservableList<String> getQueueItems() {
        return this.queueItems;
    }

    public void disconnect() {
        try {
            this.client.disconnect();
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }
    }
}