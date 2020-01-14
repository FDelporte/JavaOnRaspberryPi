package be.webtechie.pi4jgpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

import be.webtechie.pi4jgpio.handler.InputChangeEventListener;

/**
 * Based on https://www.pi4j.com/1.2/usage.html#Read_Pin_State
 */
public class App {

    private static final Pin PIN_BUTTON = RaspiPin.GPIO_05; // BCM 24

    /**
     * Reference to the listener, so we can read its values after initialization.
     */
    private static InputChangeEventListener listener;

    public static void main(String[] args) {
        System.out.println("Starting input example...");

        try {
            // Initialize the GPIO controller
            final GpioController gpio = GpioFactory.getInstance();

            // Initialize the input pin with pull down resistor
            GpioPinDigitalInput button = gpio.provisionDigitalInputPin(
                PIN_BUTTON, "Button", PinPullResistance.PULL_DOWN); 

            // Attach an event listener
            listener = new InputChangeEventListener();
            button.addListener(listener);

            // Loop until the button has been pressed 10 times
            while (listener.getNumberOfPresses() < 10) {
                Thread.sleep(10);
            }

            // Shut down the GPIO controller
            gpio.shutdown();

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
