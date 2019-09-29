package be.webtechie.javafxmosquitto.client;

import java.util.UUID;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.MqttMessage;

public class Client {

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