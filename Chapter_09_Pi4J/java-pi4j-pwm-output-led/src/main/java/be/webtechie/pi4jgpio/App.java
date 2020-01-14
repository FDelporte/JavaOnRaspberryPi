package be.webtechie.pi4jgpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.CommandArgumentParser;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/PwmExample.java
 */
public class App {
    public static final int MAX_PMW_VALUE = 1000;
    public static final int FADE_STEPS = 10;

    public static void main( String[] args ) {
        System.out.println("Starting PWM output example...");

        try {
            // Initialize the GPIO controller
            GpioController gpio = GpioFactory.getInstance();

            // All Raspberry Pi models support a hardware PWM pin on GPIO_01.
            // Raspberry Pi models A+, B+, 2B, 3B also support hardware PWM pins: GPIO_23, GPIO_24, GPIO_26
            //
            // by default we will use gpio pin #01; however, if an argument
            // has been provided, then lookup the pin by address
            Pin pin = CommandArgumentParser.getPin(
                    RaspiPin.class,    // pin provider class to obtain pin instance from
                    RaspiPin.GPIO_01,  // default pin if no pin argument found = BCM 18
                    args);             // argument array to search in

            GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(pin);

            // you can optionally use these wiringPi methods to further customize the PWM generator
            // see: http://wiringpi.com/reference/raspberry-pi-specifics/
            com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
            com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
            com.pi4j.wiringpi.Gpio.pwmSetClock(5);

            // Loop through PWM values 10 times
            for (int loop = 0; loop < 10; loop++) {
                for (int useValue = MAX_PMW_VALUE; useValue >= 0; useValue-=MAX_PMW_VALUE/FADE_STEPS) {
                    pwm.setPwm(useValue);
                    System.out.println("PWM rate is: " + pwm.getPwm());
                    
                    Thread.sleep(200);
                }
            }

            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
