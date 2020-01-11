package be.webtechie.pi4jgpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import be.webtechie.pi4jgpio.handler.InputChangeEventListener;

/**
 * Based on https://www.pi4j.com/1.2/usage.html#Read_Pin_State
 */
public class App {

    /**
     * Reference to the listener, so we read its values after initialization.
     */
    private static InputChangeEventListener listener;

    public static void main( String[] args ) {
        System.out.println("Starting input example...");

        try {
            // Initialize the GPIO controller
            final GpioController gpio = GpioFactory.getInstance();

            // Initialize WiringPi 5 as a digital input
            //  --> With its internal pull down resistor enabled
            GpioPinDigitalInput button = gpio.provisionDigitalInputPin(
                RaspiPin.GPIO_05,            // PIN NUMBER
                "Button",                      // PIN FRIENDLY NAME (optional)
                PinPullResistance.PULL_DOWN);  // PIN RESISTANCE (optional)

            // Attach an event listener
            listener = new InputChangeEventListener();
            button.addListener(listener);

            while (listener.getNumberOfPresses() < 10) {
                Thread.sleep(10);
            }

            // Stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
