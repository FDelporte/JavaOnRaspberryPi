package be.webtechie.pi4jgpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Based on https://www.pi4j.com/1.2/example/control.html
 */
public class App {
    private static final Pin PIN_RED = RaspiPin.GPIO_23;    // BCM 13
    private static final Pin PIN_GREEN = RaspiPin.GPIO_26;  // BCM 12
    private static final Pin PIN_BLUE = RaspiPin.GPIO_14;   // BCM 11

    public static void main( String[] args ) {
        System.out.println("Starting output example...");

        try {
            // Initialize the GPIO controller
            final GpioController gpio = GpioFactory.getInstance();

            // Initialize the led pins as a digital output pin with initial low state
            final GpioPinDigitalOutput ledRed = gpio.provisionDigitalOutputPin(PIN_RED, "RED", PinState.LOW);
            final GpioPinDigitalOutput ledGreen = gpio.provisionDigitalOutputPin(PIN_GREEN, "GREEN", PinState.LOW);
            final GpioPinDigitalOutput ledBlue = gpio.provisionDigitalOutputPin(PIN_BLUE, "BLUE", PinState.LOW);

            // Set the shutdown state
            ledRed.setShutdownOptions(true, PinState.LOW);
            ledGreen.setShutdownOptions(true, PinState.LOW);
            ledBlue.setShutdownOptions(true, PinState.LOW);

            // Toggle 10 times RED on and off 
            for (int led = 1; led <= 3; led++) {
                GpioPinDigitalOutput useLed = led == 1 ? ledRed : (led == 2 ? ledGreen : ledBlue);

                for (int i = 0; i < 10; i++) {
                    useLed.toggle();
                    Thread.sleep(150);

                    System.out.println("State of the LED " + useLed.getName() + " has been toggled");
                }

                // Make sure the led is off
                useLed.low();
                System.out.println("LED " + useLed.getName() + " is off");
            }

            Thread.sleep(1000);

            // All three on, should be white
            ledRed.high();
            ledGreen.high();
            ledBlue.high();

            System.out.println("All three on, check if this looks like white...");

            Thread.sleep(5000);

            // Shut down the GPIO controller
            gpio.shutdown();

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
