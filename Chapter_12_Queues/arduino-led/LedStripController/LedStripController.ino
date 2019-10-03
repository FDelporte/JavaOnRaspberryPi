#include <FastLED.h>
#define NUM_LEDS 5
#define DATA_PIN 6

// Test messages: 
// 1:25:0:127:0
// 3:250:255:0:0:0:0:250

int currentAction = 0;

byte commandId = 0;

int animationSpeed = 100;

byte r1 = 0;
byte g1 = 0;
byte b1 = 0;
byte r2 = 0;
byte g2 = 0;
byte b2 = 0;

char input[50];

CRGB leds[NUM_LEDS];

int incomingByte = 0; // for incoming serial data
    
void setup() {
  Serial.begin(9600);  
  
  FastLED.addLeds<NEOPIXEL, DATA_PIN>(leds, NUM_LEDS);
  FastLED.clear();
  
  String message = "3:250:255:0:0:0:0:250";
  message.toCharArray(input, 50);
  input[50] = 0;
}

void loop() {
  checkSerial();
  handleMessage();

  if (commandId == 1) {
    // FIXED color
    setStaticColor();
  } else if (commandId == 2) {
    setBlinking(); 
  } else if (commandId == 3) {
    setRunningLight(); 
  } else if (commandId == 4) {
    setRainbow();
  }

  delay(animationSpeed);
}

void setStaticColor() {
  for (int i = 0; i < NUM_LEDS; i++) {
    leds[i].r = r1;
    leds[i].g = g1;
    leds[i].b = b1;
  }
  
  FastLED.show(); 
}

void setBlinking() {  
  for (int i = 0; i < NUM_LEDS; i++) {
    leds[i].r = currentAction == 1 ? r1 : r2;
    leds[i].g = currentAction == 1 ? g1 : g2;
    leds[i].b = currentAction == 1 ? b1 : b2;
  }
  
  FastLED.show(); 

  currentAction++;

  if (currentAction > 2) {
    currentAction = 1;
  }
}

void setRunningLight() {  
  if (currentAction >= NUM_LEDS) {    
    currentAction = 0;    
  }
  
  // Show color 1
  leds[currentAction].r = r1;
  leds[currentAction].g = g1;
  leds[currentAction].b = b1;
  FastLED.show(); 

  // Reset to color 2 for next loop
  leds[currentAction].r = r2;
  leds[currentAction].g = g2;
  leds[currentAction].b = b2;
  
  currentAction++;
}

void setRainbow() {
  // TODO
}

void checkSerial() {
  if (Serial.available() > 0) {
    String message = Serial.readString();

    Serial.print("Received from serial: ");
    Serial.println(message);

    message.toCharArray(input, 50);
    input[50] = 0;
  }
}

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
