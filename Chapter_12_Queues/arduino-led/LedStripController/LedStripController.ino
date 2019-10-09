// Test messages: 
// 1:25:0:127:0
// 2:5:255:0:0:0:0:250
// 3:25:255:0:0:0:0:250
// 4:5:255:0:0:0:0:250
// 5:5
// 6

// Define the number of LEDS in your strip here
const int numberOfLeds = 11;

// Define the type of connections you want to enable
bool useWireless = false; // to commmunicate via NRF24L01
bool useWifiMosquitto = false; // to get messages from Mosquitto via Wifi

// Define the Wifi settings if "useWifi=true"
bool scanWifiNetworks = true;
char ssid[] = "******";    //  your network SSID (name)
char pass[] = "******";       // your network password

// Define the Mosquitto settings if "useMosquitto=true"
const char* mqtt_server = "192.168.0.213";
const char* queue_topic = "ledCommand";

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
  while(!Serial);

  // Initialize the leds
  initLeds();

  // Initialize the configured connections
  if (useWifiMosquitto) {
    initWifi();
    initMosquitto();
  }

  if (useWireless) {
    //initWireless();
  }

  // Set the initial LED effect
  String message = "6:";
  message.toCharArray(input, 50);
}

void loop() {
  checkSerial();

  if (useWifiMosquitto) {
    checkMosquitto();
  }

  if (useWireless) {
    //checkWireless();
  }
  
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
    } else if (commandId == 255) {
      clearLeds();
    }

    currentLoop = 0;
  }
}
