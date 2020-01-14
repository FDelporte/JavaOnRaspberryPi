package be.webtechie.pi4jgpio.handler;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Listener which will be called each time the button is pressed.
 */
public class InputChangeEventListener implements GpioPinListenerDigital {
    private int numberOfPresses = 0;
    private long lastPress = System.currentTimeMillis();
  
    /**
     * Event handler
     */
    @Override
    public void handleGpioPinDigitalStateChangeEvent(
        GpioPinDigitalStateChangeEvent event) {

        if (event.getState() == PinState.HIGH) {
            this.numberOfPresses++;

            long diff = System.currentTimeMillis() - this.lastPress;

            System.out.println("Button pressed for " 
                + this.numberOfPresses + "th time, diff: "
                + diff + "millis");

            this.lastPress = System.currentTimeMillis();
        }
    }

    /**
     * @return the number of times the button has been pressed
     */
    public int getNumberOfPresses() {
        return this.numberOfPresses;
    }
}