package be.webtechie.pi4jgpio;

import com.pi4j.Pi4J;

/**
 * Based on https://www.pi4j.com/1.2/example/control.html
 */
public class App {
    private static final int PIN_RED = 13;    // BCM 13
    private static final int PIN_GREEN = 12;  // BCM 12
    private static final int PIN_BLUE = 11;   // BCM 11

    static void main(String[] args) {
        System.out.println("Starting output example...");

        try {
            // Initialize Pi4J
            var pi4j = Pi4J.newAutoContext();

            // Initialize the led pins as a digital output pin with initial low state
            var ledRed = pi4j.digitalOutput().create(PIN_RED, "RED");
            var ledGreen = pi4j.digitalOutput().create(PIN_GREEN, "GREEN");
            var ledBlue = pi4j.digitalOutput().create(PIN_BLUE, "BLUE");

            // Toggle 10 times RED on and off 
            for (int led = 1; led <= 3; led++) {
                var useLed = led == 1 ? ledRed : (led == 2 ? ledGreen : ledBlue);

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

            // Shut down Pi4J
            pi4j.shutdown();

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
