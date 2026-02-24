void checkSerial() {
  if (Serial.available() > 0) {
    String message = Serial.readString();

    Serial.print("Received from serial: ");
    Serial.println(message);

    message.toCharArray(input, 50);
    input[50] = 0;
  }
}
