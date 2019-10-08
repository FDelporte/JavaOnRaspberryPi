#include <PubSubClient.h>

PubSubClient client;

void initMosquitto() {
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void checkMosquitto() {
  if (!client.connected()) {
    reconnectMosquitto();
  }
 
  client.loop();
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("]: ");

  strncpy(input, (char*) payload, sizeof(input));
 
  Serial.println(input);
}

void reconnectMosquitto() {
  while (!client.connected()) {
    Serial.println("Attempting MQTT connection...");
    
    if (client.connect("Arduino")) {
      Serial.println("Connected");
      client.subscribe("led\ledCommand");
    } else {
      Serial.print("Failed, rc=");
      Serial.print(client.state());
      Serial.println(", will try again in 5 seconds");
    
      delay(5000);
    }
  }
}
