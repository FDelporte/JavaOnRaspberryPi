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

// Define the Wifi settings if "useWifi=true"
bool scanWifiNetworks = true;
char ssid[] = "******";    //  your network SSID (name)
char pass[] = "******";       // your network password

// Define the Mosquitto settings if "useMosquitto=true"
const char broker[] = "192.168.0.213";
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
byte r1 = 0;
byte g1 = 0;
byte b1 = 0;
byte r2 = 0;
byte g2 = 0;
byte b2 = 0;

int currentLoop = 0;

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

  // Connecting to Mosquitto  
  mqttClient.onMessage(onMqttMessage);
  if (!mqttClient.connect(broker, port)) {
    Serial.print("MQTT connection failed! Error code = ");
    Serial.println(mqttClient.connectError());
  } else {
    mqttClient.subscribe(topic);
  }

  // Set the initial LED effect
  String message = "2:5:255:0:0:0:0:250";
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
      r1 = 255;
      g1 = 255;
      b1 = 255;
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
}
