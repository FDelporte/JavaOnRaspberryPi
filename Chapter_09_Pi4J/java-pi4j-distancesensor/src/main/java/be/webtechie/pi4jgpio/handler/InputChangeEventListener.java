package be.webtechie.pi4jgpio.handler;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Listener which will be called each time the button is pressed.
 */
public class InputChangeEventListener implements GpioPinListenerDigital {
    private long measurementStart;
    private long measurementEnd;

    /**
     * Event handler
     */
    @Override
    public void handleGpioPinDigitalStateChangeEvent(
        GpioPinDigitalStateChangeEvent event) {

        if (event.getState() == PinState.HIGH) {
            System.out.println("Input high");
            this.measurementEnd = System.nanoTime();
        }
    }

    /**
     * @return the number of times the button has been pressed
     */
    public void startMeasurement() {
        this.measurementStart = System.nanoTime();
        this.measurementEnd = 0;
    }

    public long getMeasurement() {
        return this.measurementEnd == 0 ? 0 : this.measurementEnd - this.measurementStart;
    }
}