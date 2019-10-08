#include <WiFi.h>

int wifiStatus = WL_IDLE_STATUS;

void initWifi() {
  // Setup Wifi connection
    Serial.println("Initializing Wifi...");
    printMacAddress();

    if (scanWifiNetworks) {
      // Scan for existing networks:
      Serial.println("Scanning available networks...");
      listNetworks();
    }
    
    // Attempt to connect using WPA2 encryption:
    Serial.println("Attempting to connect to WPA network...");
    wifiStatus = WiFi.begin(ssid, pass);
  
    // if you're not connected, stop here:
    if (wifiStatus != WL_CONNECTED) {
      Serial.println("WIFI ERROR: couldn't get a connection");
    } else {
      Serial.println("Connected to Wifi network");
    }
}

void printMacAddress() {
  byte mac[6];                    

  WiFi.macAddress(mac);
  
  Serial.print("MAC: ");
  Serial.print(mac[5],HEX);
  Serial.print(":");
  Serial.print(mac[4],HEX);
  Serial.print(":");
  Serial.print(mac[3],HEX);
  Serial.print(":");
  Serial.print(mac[2],HEX);
  Serial.print(":");
  Serial.print(mac[1],HEX);
  Serial.print(":");
  Serial.println(mac[0],HEX);
}

void listNetworks() {
  Serial.println("Scanning Wifi networks");
  byte numSsid = WiFi.scanNetworks();

  Serial.print("Available networks: ");
  Serial.println(numSsid);

  // Print the network number and name for each network found:
  for (int thisNet = 0; thisNet<numSsid; thisNet++) {
    Serial.print(thisNet);
    Serial.print(") ");
    Serial.print(WiFi.SSID(thisNet));
    Serial.print("\tSignal: ");
    Serial.print(WiFi.RSSI(thisNet));
    Serial.print(" dBm");
    Serial.print("\tEncryption: ");
    Serial.println(WiFi.encryptionType(thisNet));
  }
}
