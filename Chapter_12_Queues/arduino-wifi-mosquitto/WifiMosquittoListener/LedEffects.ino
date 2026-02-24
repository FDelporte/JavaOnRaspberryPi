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
    strip.setPixelColor(i, rgb1);
  }

  strip.show();
}

void setStaticFade() {  
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, getGradientColor(i));
  }

  strip.show();
}

void setBlinking() {  
  for(uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, currentAction == 1 ? rgb1 : rgb2);
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
  strip.setPixelColor(currentAction, rgb1);
  strip.show(); 

  // Reset to color 2 for next loop
  strip.setPixelColor(currentAction, rgb2);
  
  currentAction++;
}

void setFadingRainbow() {
  if (currentAction > 255) {
    currentAction = 0;
  }

  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, getWheelColor((i * 1 + currentAction) & 255));
  }
  
  strip.show();
  
  currentAction++;
}

void setStaticRainbow() {
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, getWheelColor((255 / strip.numPixels()) * i));
  }
  
  strip.show();
}

// Calculate a gradient color between color 1 and 2, for the given position
uint32_t getGradientColor(uint16_t pos) {
  float factor = ((float) pos / (float) (strip.numPixels() - 1));
  
  byte r1 = (rgb1 & 0xFF0000) >> 16;
  byte g1 = (rgb1 & 0x00FF00) >> 8;
  byte b1 = (rgb1 & 0x0000FF);

  byte r2 = (rgb2 & 0xFF0000) >> 16;
  byte g2 = (rgb2 & 0x00FF00) >> 8;
  byte b2 = (rgb2 & 0x0000FF);

  byte r = (factor * r2) + ((1 - factor) * r1);
  byte g = (factor * g2) + ((1 - factor) * g1);
  byte b = (factor * b2) + ((1 - factor) * b1);

  uint32_t rt = strip.Color(r, g, b);

  /*
  Serial.print("Position ");
  Serial.print(pos);
  Serial.print(", factor ");
  Serial.print(factor);
  Serial.print(", gradient color: ");
  Serial.println(String(rt, HEX));
  */
  
  return rt;
}

// Pos from 0 to 255 to get colors from full color wheel
// 0 - 85:    G - R
// 85 - 170:  R - B
// 170 - 255: B - G
uint32_t getWheelColor(byte pos) {
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
