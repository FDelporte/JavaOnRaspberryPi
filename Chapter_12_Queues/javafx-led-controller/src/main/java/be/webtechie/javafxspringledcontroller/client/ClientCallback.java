package be.webtechie.javafxspringledcontroller.client;

import be.webtechie.javafxspringledcontroller.event.EventManager;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ClientCallback implements MqttCallback {

    final EventManager eventManager;

    public ClientCallback(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message received:\n\t" + new String(mqttMessage.getPayload()));

        Platform.runLater(() -> {
            eventManager.sendEvent(new String(mqttMessage.getPayload()));
        });
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivery complete");
    }
}