package be.webtechie.pi4jgpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Based on https://www.pi4j.com/1.2/example/control.html
 */
public class App {
    public static void main( String[] args ) {
        System.out.println("Starting output example...");

        try {
            // Initialize the GPIO controller
            final GpioController gpio = GpioFactory.getInstance();

            // Initialize WiringPi 3 as a digital output pin with high state
            final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "LED", PinState.LOW);

            // Set the shutdown state
            led.setShutdownOptions(true, PinState.LOW);

            // Toggle 10 times on and off 
            for (int i = 0; i < 10; i++) {
                led.toggle();
                Thread.sleep(500);

                System.out.println("State of the LED has been toggled");
            }

            // Make sure the led is off
            led.low();
            System.out.println("LED is off");

            Thread.sleep(1000);

            // Put the LED on for 2.5 seconds
            // The second parameter when true forces this call to block the application
            led.pulse(2500, true); 
            System.out.println("Putting LED on for 2.5 seconds");

            // Stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
