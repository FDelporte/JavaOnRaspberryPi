package be.webtechie.pi4jgpio.handler;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class InputChangeEventListener implements GpioPinListenerDigital {
    private int numberOfPresses = 0;
  
    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState() == PinState.HIGH) {
            this.numberOfPresses++;
            System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() 
                + " = " + event.getState()
                + ", number of presses: " + this.numberOfPresses);

        }
    }

    public int getNumberOfPresses() {
        return this.numberOfPresses;
    }
}