package be.webtechie.javafxmosquitto.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class QueueClient {

    final static String PI_ADDRESS = "192.168.0.213";

    private MqttClient client;

    public QueueClient() {
        try {
            this.client = new MqttClient("tcp://" + PI_ADDRESS + ":1883", MqttClient.generateClientId());
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
            return;
        }

        this.client.setCallback(new ClientCallback());

        try {
            this.client.connect();
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }

        this.sendMessage("testing/testTopic", "lalala");
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

    public void disconnect() {
        try {
            this.client.disconnect();
        } catch (MqttException ex) {
            System.err.println("MqttException: " + ex.getMessage());
        }
    }
}