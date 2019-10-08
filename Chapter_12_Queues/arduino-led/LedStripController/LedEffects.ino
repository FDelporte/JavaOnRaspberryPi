#include <SPI.h>
#include <FastLED.h>

CRGB leds[numberOfLeds];

int currentAction = 0;

void initLeds() {
  // Configure FastLED for every pin on which a LED strip is connected
  FastLED.addLeds<NEOPIXEL, 6>(leds, numberOfLeds);
  
  // If you use multiple led strips who need to show the same colors,
  // uncomment one or more of the following lines and attach the led strep
  // to the defined pin 7, 8 or other...
  // FastLED.addLeds<NEOPIXEL, 7>(leds, numberOfLeds);
  FastLED.addLeds<NEOPIXEL, 8>(leds, numberOfLeds);

  // Clear all the leds so they all start from the same point
  FastLED.clear();
}

void clearLeds() {
  FastLED.clear();
}

void setStaticColor() {
  for (int i = 0; i < numberOfLeds; i++) {
    leds[i].r = r1;
    leds[i].g = g1;
    leds[i].b = b1;
  }
  
  FastLED.show(); 
}

void setStaticFade() {
  fill_gradient_RGB(leds, numberOfLeds, CRGB(r1, g1, b1), CRGB(r2, g2, b2));
  FastLED.show(); 
}

void setBlinking() {  
  for (int i = 0; i < numberOfLeds; i++) {
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
  if (currentAction >= numberOfLeds) {    
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

void setFadingRainbow() {
  currentAction++;

  if (currentAction > 255) {
    currentAction = 0;
  }
  
  int pos = beatsin16(5, 35, 255); // generating the sinwave 
  
  fill_solid(leds, numberOfLeds, CHSV(currentAction, 255, pos)); // CHSV (hue, saturation, value);
  FastLED.show();
}

void setStaticRainbow() {
  currentAction++;

  if (currentAction > 255) {
    // This one needs a delay because the gradient calculation slows down too much
    currentAction = 0;

    fill_gradient_RGB(leds, numberOfLeds, CRGB::Blue, CRGB::Red); 
    FastLED.show();
  }
}
