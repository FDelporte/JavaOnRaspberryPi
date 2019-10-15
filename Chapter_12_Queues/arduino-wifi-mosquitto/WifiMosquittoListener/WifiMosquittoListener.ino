#include <SPI.h>
#include <WiFiNINA.h>
#include <ArduinoMqttClient.h>
#include <Adafruit_NeoPixel.h>

// Test messages: 
// 1:25:0:127:0
// 2:5:255:0:0:0:0:250
// 3:25:255:0:0:0:0:250
// 4:5:255:0:0:0:0:250
// 5:5
// 6

// Define the number of LEDS in your strip here
#define PIN 6
#define NUMBER_OF_LEDS 11

// WiFi settings
char ssid[] = "******";    //  your network SSID (name)
char pass[] = "******";       // your network password

// Mosquitto settings
const char broker[] = "192.168.0.142";
int        port     = 1883; // default broker mqtt port, normally you don't need to change this 
const char topic[]  = "ledCommand";

// Variables used by the WiFi and Mosquitto connection handler
WiFiClient wifiClient;
MqttClient mqttClient(wifiClient);

// Variables used by the code to handle the incoming LED commands
char input[50];
int incomingByte = 0;

// Variables defined by the incoming LED commands
byte commandId = 0;
int animationSpeed = 10;
uint32_t rgb1 = 0;
uint32_t rgb2 = 0;

int currentLoop = 0;

// LED strip initialization
// Parameter 1 = number of pixels in strip
// Parameter 2 = Arduino pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
//   NEO_RGBW    Pixels are wired for RGBW bitstream (NeoPixel RGBW products)
Adafruit_NeoPixel strip = Adafruit_NeoPixel(NUMBER_OF_LEDS, PIN, NEO_GRB + NEO_KHZ800);

// Code executed at startup of the Arduino board
void setup() {
  // Configure serial speed and wait till it is available
  // This is used to output logging info and can receive LED commands
  Serial.begin(9600);  
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  // Initialize the leds
  initLeds();

  // Connecting to WiFi
  Serial.println("--- Connecting to WiFi ---");
  while (WiFi.begin(ssid, pass) != WL_CONNECTED) {
    // failed, retry
    Serial.print(".");
    delay(5000);
  }

  Serial.print("Connected to WiFi network '");
  Serial.print(WiFi.SSID());
  Serial.print("' with IP ");
  Serial.print(WiFi.localIP());
  Serial.print(" MAC ");
  printMacAddress();
  Serial.println("");
  
/*
  // Connecting to Mosquitto  
  Serial.println("");
  Serial.println("--- Connecting to Mosquitto ---");
  mqttClient.onMessage(onMqttMessage);
  if (!mqttClient.connect(broker, port)) {
    Serial.print("MQTT connection failed! Error code = ");
    Serial.println(mqttClient.connectError());
  } else {
    mqttClient.subscribe(topic);
    Serial.println("MQTT connection ready");
  }

  Serial.println("");
  */
  // Set the initial LED effect
  String message = "2:0:14:255:0:255:5:0";
  message.toCharArray(input, 50);
}

void loop() {  
  mqttClient.poll();
  checkSerial();
  handleMessage();
  
  currentLoop++;

  // Only do LED effect when loop exceeds the defined animationSpeed
  if (currentLoop >= animationSpeed) {
    // Depending on the commandId, call the correct LED effect
    if (commandId == 1) {
      setStaticColor();
    } else if (commandId == 2) {
      setStaticFade();
    } else if (commandId == 3) {
      setBlinking(); 
    } else if (commandId == 4) {
      setRunningLight(); 
    } else if (commandId == 5) {
      setFadingRainbow(); 
    } else if (commandId == 6) {
      setStaticRainbow(); 
    } else if (commandId == 98) {
      rgb1 = strip.Color(255, 255, 255);
      setStaticColor();
    } else if (commandId == 99) {
      clearLeds();
    } else {
      Serial.print("Command ");
      Serial.print(commandId);
      Serial.println(" is not implemented");
    }

    currentLoop = 0;
  }

  delay(5);
}

void printMacAddress() {
  byte bssid[6];
  WiFi.BSSID(bssid);
  
  for (int i = 5; i >= 0; i--) {
    if (bssid[i] < 16) {
      Serial.print("0");
    }
    Serial.print(bssid[i], HEX);
    if (i > 0) {
      Serial.print(":");
    }
  }
}
