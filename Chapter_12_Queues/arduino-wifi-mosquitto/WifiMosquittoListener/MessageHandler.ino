void handleMessage() {
  if (input[0] == 0) {
    return;
  }

  Serial.print("New message received: ");
  Serial.println(input);
  
  char* part = strtok(input, ":");
  int position = 0;

  byte r1 = 0;
  byte g1 = 0;
  byte b1 = 0;
  byte r2 = 0;
  byte g2 = 0;
  byte b2 = 0;
  
  while (part != 0) {    
    int value = atoi(part);
    
    if (position == 0) {
      commandId = value;
    } else if (position == 1) {
      animationSpeed = value;
    } else if (position == 2) {
      r1 = value;
    } else if (position == 3) {
      g1 = value;
    } else if (position == 4) {
      b1 = value;
    } else if (position == 5) {
      r2 = value;
    } else if (position == 6) {
      g2 = value;
    } else if (position == 7) {
      b2 = value;
    }

    position++;
    
    // Find the next command in input string
    part = strtok(0, ":");
  }

  input[0] = 0;

  rgb1 = strip.Color(r1, g1, b1);
  rgb2 = strip.Color(r2, g2, b2);
  
  Serial.print("Parsed values: command: ");
  Serial.print(commandId);
  Serial.print(", speed: ");
  Serial.print(animationSpeed);
  Serial.print(", Color 1: RGB ");
  Serial.print(r1);
  Serial.print("/");
  Serial.print(g1);
  Serial.print("/");
  Serial.print(b1);
  Serial.print(" = ");
  Serial.print(String(rgb1, HEX));
  Serial.print(", Color 2: R/G/B: ");
  Serial.print(r2);
  Serial.print("/");
  Serial.print(g2);
  Serial.print("/");
  Serial.print(b2);
  Serial.print(" = ");
  Serial.print(String(rgb2, HEX));
  Serial.println("");
}
