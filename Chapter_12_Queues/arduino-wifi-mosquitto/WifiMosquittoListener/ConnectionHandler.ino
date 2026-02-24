void onMqttMessage(int messageSize) {
  while (mqttClient.available()) {
    mqttClient.read(input, sizeof(input));
    input[50] = 0;
  }

  Serial.print("Received a message with topic '");
  Serial.print(mqttClient.messageTopic());
  Serial.print("', length ");
  Serial.print(messageSize);
  Serial.print(", content:");
  Serial.println(input);
}
