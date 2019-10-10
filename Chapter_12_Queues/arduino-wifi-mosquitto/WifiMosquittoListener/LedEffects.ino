// Parameter 1 = number of pixels in strip
// Parameter 2 = Arduino pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
//   NEO_RGBW    Pixels are wired for RGBW bitstream (NeoPixel RGBW products)
Adafruit_NeoPixel strip = Adafruit_NeoPixel(NUMBER_OF_LEDS, PIN, NEO_GRB + NEO_KHZ800);

int currentAction = 0;

void initLeds() {
  strip.begin();
  strip.setBrightness(100);
  strip.clear();
  strip.show(); // Initialize all pixels to 'off'
}

void clearLeds() {
  strip.clear(); 
  strip.show();
}

void setStaticColor() {
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, strip.Color(r1, g1, b1));
  }

  strip.show();
}

void setStaticFade() {
  uint32_t c1 = strip.Color(r1, g1, b1);
  uint32_t c2 = strip.Color(r2, g2, b2);
  
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    float num = strip.numPixels() * 1.00;
    float p1 = (num - i) / num;
    float p2 = i / (num - 1);

    /* Serial.print(i);
    Serial.print("\t");
    Serial.print(p1);
    Serial.print("\t");
    Serial.print(p2);
    Serial.println(""); */
    
    strip.setPixelColor(i, strip.Color(
      (r1 * p1) + (r2 * p2),
      (g1 * p1) + (g2 * p2),
      (b1 * p1) + (b2 * p2)
    ));
  }

  strip.show();
}

void setBlinking() {  
  for(uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, currentAction == 1 ? strip.Color(r1, g1, b1) : strip.Color(r2, g2, b2));
  }

  strip.show();

  currentAction++;

  if (currentAction > 2) {
    currentAction = 1;
  }
}

void setRunningLight() {  
  if (currentAction >= NUMBER_OF_LEDS) {    
    currentAction = 0;    
  }
  
  // Show color 1
  strip.setPixelColor(currentAction, strip.Color(r1, g1, b1));
  strip.show(); 

  // Reset to color 2 for next loop
  strip.setPixelColor(currentAction, strip.Color(r2, g2, b2));
  
  currentAction++;
}

void setFadingRainbow() {
  if (currentAction > 255) {
    currentAction = 0;
  }

  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, Wheel((i * 1 + currentAction) & 255));
  }
  
  strip.show();
  
  currentAction++;
}

void setStaticRainbow() {
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, Wheel((255 / strip.numPixels()) * i));
  }
  
  strip.show();
}

// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel(byte pos) {
  if (pos < 85) {
    return strip.Color(pos * 3, 255 - pos * 3, 0);
  } else if (pos < 170) {
    pos -= 85;
    return strip.Color(255 - pos * 3, 0, pos * 3);
  } else {
    pos -= 170;
    return strip.Color(0, pos * 3, 255 - pos * 3);
  }
}
