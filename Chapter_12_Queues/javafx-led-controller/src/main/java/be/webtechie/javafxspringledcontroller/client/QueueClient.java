package be.webtechie.javafxspringledcontroller.client;

import be.webtechie.javafxspringledcontroller.event.EventManager;
import be.webtechie.javafxspringledcontroller.led.LedCommand;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class QueueClient {

    private MqttClient client;

    private final String queueTopic;

    public QueueClient(EventManager eventManager, String queueAddress, String queueTopic) {
        this.queueTopic = queueTopic;

        if (!this.initConnection(queueAddress)) {
            System.err.println("Initializing connection failed");

            return;
        }

        this.connect();
        this.subscribe(eventManager);
    }

    private boolean initConnection(String ipAddress) {
        try {
            this.client = new MqttClient("tcp://" + ipAddress + ":1883", MqttClient.generateClientId());
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

    private void subscribe(EventManager eventManager) {
        try {
            this.client.setCallback(new ClientCallback(eventManager));

            this.client.subscribe(this.queueTopic);
        } catch (MqttException ex) {
            System.err.println("Error while subscribing: " + ex.getMessage());
        }
    }

    public void sendMessage(LedCommand ledCommand) {
        this.sendMessage(ledCommand.toCommandString());
    }

    public void sendMessage(String messageText) {
        if (!this.client.isConnected()) {
            System.err.println("The queue client is not connected!");
            this.connect();
        }

        MqttMessage message = new MqttMessage();
        message.setPayload(messageText.getBytes());

        try {
            this.client.publish(this.queueTopic, message);
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }
    }

    public void disconnect() {
        try {
            this.client.disconnect();
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }
    }
}