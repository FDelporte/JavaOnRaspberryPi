#include <FastLED.h>
#define NUM_LEDS 5
#define DATA_PIN 6

int currentPin = 0;

byte commandId = 0;

byte animationSpeed = 100;

byte r1 = 0;
byte g1 = 0;
byte b1 = 0;

char input[50];

CRGB leds[NUM_LEDS];

int incomingByte = 0; // for incoming serial data
    
void setup() {
  Serial.begin(9600);  
  
  FastLED.addLeds<NEOPIXEL, DATA_PIN>(leds, NUM_LEDS);
  FastLED.clear();
  
  String message = "1:25:0:127:0";
  message.toCharArray(input, 50);
  input[50] = 0;
}

void loop() {
  checkSerial();
  handleMessage();

  if (commandId == 1) {
    // FIXED color
    for (int i = 0; i < NUM_LEDS; i++) {
      leds[i].r = r1;
      leds[i].g = g1;
      leds[i].b = b1;
    }
    
    FastLED.show(); 
    delay(animationSpeed);
  } else {  
    leds[currentPin].r = 0;
    leds[currentPin].g = 0;
    leds[currentPin].b = 0;
    FastLED.show(); 
    delay(animationSpeed);
  
    currentPin++;
  
    if (currentPin > NUM_LEDS) {
      currentPin = 0;
    }
  }
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
    }
    
    if (commandId == 1) {
      if (position == 2) {
        r1 = value;
      } else if (position == 3) {
        g1 = value;
      } else if (position == 4) {
        b1 = value;
      }
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
  Serial.print(", R: ");
  Serial.print(r1);
  Serial.print(", G: ");
  Serial.print(g1);
  Serial.print(", B: ");
  Serial.print(b1);
  Serial.println("");
}
