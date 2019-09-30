package be.webtechie.javafxmosquitto.client;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class QueueClient {

    final static String PI_ADDRESS = "192.168.0.213";

    final MqttClient client;

    public Client() {
        this.client = new MqttClient("tcp://" + PI_ADDRESS + ":1883", MqttClient.generateClientId());
        this.client.setCallback(new ClientCallback());
        this.client.connect();
    }

    public void sendMessage(String topic, String message) {
        MqttMessage message = new MqttMessage();
        message.setPayload(message.getBytes());
        
        this.client.publish(topic, message);
    }

    public void disconnect() {
        this.client.disconnect();
    }
}