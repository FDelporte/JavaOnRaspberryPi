package be.webtechie.pi4jgpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

import be.webtechie.pi4jgpio.handler.InputChangeEventListener;

public class App {

    private static final Pin PIN_TRIGGER = RaspiPin.GPIO_01;    // BCM 18
    private static final Pin PIN_ECHO = RaspiPin.GPIO_05;       // BCM 24

    private static GpioPinDigitalOutput trigger;
    private static GpioPinDigitalInput echo;

    /**
     * Reference to the listener, so we can read its values after initialization.
     */
    private static InputChangeEventListener listener;

    public static void main(String[] args) {
        System.out.println("Starting distance sensor example...");

        try {
            // Initialize the GPIO controller
            GpioController gpio = GpioFactory.getInstance();

            // Initialize the pins
            trigger = gpio.provisionDigitalOutputPin(PIN_TRIGGER, "Trigger", PinState.LOW); 
            echo = gpio.provisionDigitalInputPin(PIN_ECHO, "Echo", PinPullResistance.PULL_UP); 

            // Attach an event listener
            listener = new InputChangeEventListener();
            echo.addListener(listener);

            // Loop and measure the distance 5 times per second
            while (true) {
                measureDistance();

                Thread.sleep(2000);
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    private static void measureDistance() {
        try {
            // Trigger must be high to start measurement
            trigger.high();
            System.out.println(System.nanoTime() + " high");

            // Wait for 0.01ms and put trigger low
            nanoSleep(1000);
            trigger.low();
            System.out.println(System.nanoTime() + " low");

            // Initialize times
            listener.startMeasurement();
 
            // Wait till input is received
            while (listener.getMeasurement() == 0) {
                // Waiting...
                Thread.sleep(10);
            }

            // Calculate distance based on the speed of sound = 343°° cm/s
            float distance = listener.getMeasurement() * 34300F;

            // Distance is till bounce and back
            distance = distance / 2;

            System.out.println("Measured distance is: " + distance + "cm");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    private static void nanoSleep(int nanos) {
        long end = System.nanoTime() + nanos;
        while (System.nanoTime() < end) { 
            // Waiting... 
        }
    }
}
