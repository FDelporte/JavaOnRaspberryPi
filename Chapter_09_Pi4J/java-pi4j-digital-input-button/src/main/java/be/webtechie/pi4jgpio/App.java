package be.webtechie.pi4jgpio;

import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.util.Console;

/**
 * Based on https://www.pi4j.com/1.2/usage.html#Read_Pin_State
 */
public class App {

    private static final int PIN_BUTTON = 24; // BCM 24

    private static int numberOfPresses = 0;
    private static long lastPress = System.currentTimeMillis();

    static void main(String[] args) {
        System.out.println("Starting input example...");

        try {
            // Create Pi4J console wrapper/helper
            // (This is a utility class to abstract some of the boilerplate stdin/stdout code)
            var console = new Console();

            // Initialize Pi4J
            var pi4j = Pi4J.newAutoContext();

            // Initialize the input pin with pull down resistor
            var buttonConfig = DigitalInput.newConfigBuilder(pi4j)
                    .id("button")
                    .name("Press button")
                    .address(PIN_BUTTON)
                    .pull(PullResistance.PULL_DOWN)
                    .debounce(3000L);

            var button = pi4j.create(buttonConfig);

            button.addListener(e -> {
                if (e.state() == DigitalState.LOW) {
                    numberOfPresses++;

                    long diff = System.currentTimeMillis() - lastPress;

                    console.println("Button pressed for "
                            + numberOfPresses + "th time, diff: "
                            + diff + "millis");

                    lastPress = System.currentTimeMillis();

                    console.println("Button was pressed for the " + numberOfPresses + "th time");
                }
            });

            // Loop until the button has been pressed 10 times
            while (numberOfPresses < 10) {
                Thread.sleep(10);
            }

            // Shutdown Pi4J
            pi4j.shutdown();

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
