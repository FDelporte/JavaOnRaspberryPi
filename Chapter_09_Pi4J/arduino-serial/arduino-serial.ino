int loopCounter = 0;
int maxCounter = 1000;
int messageCounter = 0;

void setup() {
  Serial.begin(38400);
}

void loop() {
  if (Serial.available() > 0) {
     String received = Serial.readStringUntil('\n');
     Serial.print("Echo: ");
     Serial.println(received);
  }

  loopCounter++;

  if (loopCounter > maxCounter) {
    messageCounter++;
    
    Serial.print("Message ");
    Serial.print(messageCounter);
    Serial.println(" from Arduino");
    
    loopCounter = 0;
  }
  
  delay(10);
}
