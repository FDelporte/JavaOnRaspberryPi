void handleMessage() {
  if (input[0] == 0) {
    return;
  }

  Serial.print("New message received: ");
  Serial.println(input);
  
  char* part = strtok(input, ":");
  int position = 0;
  
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

  Serial.print("Parsed values: command: ");
  Serial.print(commandId);
  Serial.print(", speed: ");
  Serial.print(animationSpeed);
  Serial.print(", color1: R: ");
  Serial.print(r1);
  Serial.print(", G: ");
  Serial.print(g1);
  Serial.print(", B: ");
  Serial.print(b1);
  Serial.print(", color2: R: ");
  Serial.print(r2);
  Serial.print(", G: ");
  Serial.print(g2);
  Serial.print(", B: ");
  Serial.print(b2);
  Serial.println("");
}
