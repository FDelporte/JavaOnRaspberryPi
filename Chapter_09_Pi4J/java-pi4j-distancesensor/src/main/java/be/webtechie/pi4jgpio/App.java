package be.webtechie.pi4jgpio;

import be.webtechie.pi4jgpio.helper.Calculation;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.concurrent.TimeUnit;

public class App {

    private static final Pin PIN_TRIGGER = RaspiPin.GPIO_01;    // BCM 18
    private static final Pin PIN_ECHO = RaspiPin.GPIO_05;       // BCM 24

    private static GpioPinDigitalOutput trigger;
    private static GpioPinDigitalInput echo;

    public static void main(String[] args) {
        System.out.println("Starting distance sensor example...");

        try {
            // Initialize the GPIO controller
            GpioController gpio = GpioFactory.getInstance();

            // Initialize the pins
            trigger = gpio.provisionDigitalOutputPin(PIN_TRIGGER, "Trigger", PinState.LOW); 
            echo = gpio.provisionDigitalInputPin(PIN_ECHO, "Echo", PinPullResistance.PULL_UP); 

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
            // Set trigger high for 0.01ms
            trigger.pulse(10, PinState.HIGH, true, TimeUnit.NANOSECONDS);

            // Start the measurement
            while (echo.isLow()) {
			    // Wait until the echo pin is high, indicating the ultrasound was sent
		    }
		    long start = System.nanoTime();

            // Wait till measurement is finished
		    while (echo.isHigh()) {
                // Wait until the echo pin is low, indicating the ultrasound was received back
		    }
		    long end = System.nanoTime();
            
            // Calculate distance based on the speed of sound = 34300 cm/s
            float measuredSeconds = Calculation.getSecondsDifference(start, end);

            System.out.println("Measured distance is: "
                    + Calculation.getDistance(measuredSeconds, true) + "cm"
                    + " for " + measuredSeconds + "s");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
