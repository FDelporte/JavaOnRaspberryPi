package be.webtechie.javafxmosquitto.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ClientCallback implements MqttCallback {

    public void connectionLost(Throwable throwable) {
      System.out.println("Connection to MQTT broker lost!");
    }
  
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
      System.out.println("Message received:\n\t"+ new String(mqttMessage.getPayload()) );
    }
  
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
      // not used in this example
    }
  }