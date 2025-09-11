package be.webtechie.javaspringrestgpio.manager;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Singleton instance for the {@link GpioFactory}.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) // Default value, added for clarity
public class Pi4JManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The GPIO controller.
     */
    private final Context pi4j;

    /**
     * Constructor which initializes the Pi4J {@link Context}.
     */
    public Pi4JManager() {
        this.pi4j = Pi4J.newAutoContext();
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name The name of the GPIO pin.
     * @return True if successful.
     */
    public boolean provisionDigitalInputPin(int address, String name) {
        // Use Pi4J Manager to provision digital input pin
        return provisionGpioPin(address, "digital_input", GpioPinPhysicalMode.IN);
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name The name of the GPIO pin.
     * @return True if successful.
     */
    public boolean provisionDigitalOutputPin(int address, String name) {
        // Use Pi4J Manager to provision digital output pin
        return provisionGpioPin(address, "digital_output", GpioPinPhysicalMode.OUTPUT);
    }

    /**
     * Provision a GPIO as analog input/output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name The name of the GPIO pin.
     * @return True if successful.
     */
    public boolean provisionAnalogInputOutputPin(int address, String name) {
        // Use Pi4J Manager to provision analog input/output pin
        return provisionGpioPin(address, "analog_input_output", GpioPinPhysicalMode.ANALOG_INPUT_OUTPUT);
    }

    /**
     * Get the current state of a pin.
     *
     * @param address The address of the GPIO pin.
     * @return The value read from the pin
     */
    public String getState(int address) {
        try {
            // Use Pi4J Manager to get the state of the pin
            return String.valueOf(getPin(address).getState().getValue());
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return ex.getMessage();
        }
    }

    /**
     * Set the state of a pin.
     *
     * @param address The address of the GPIO pin.
     * @param value Possible values: 1 (= PULL_DOWN), 2 (= PULL_UP), 0 and all other (= OFF).
     * @return True if successful.
     */
    public String setPinDigitalState(int address, long value) {
        try {
            // Use Pi4J Manager to set the state of the pin
            return String.valueOf(getPin(address).getState().setValue(value));
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return ex.getMessage();
        }
    }

    /**
     * Toggle a pin.
     *
     * @param address The address of the GPIO pin.
     * @return True if successful.
     */
    public boolean togglePin(int address) {
        try {
            // Use Pi4J Manager to toggle the state of the pin
            return getPin(address).getState().toggle();
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return false;
        }
    }

    /**
     * Pulse a pin.
     *
     * @param address The address of the GPIO pin.
     * @param duration The duration in milliseconds.
     * @return True if successful.
     */
    public boolean pulsePin(int address, long duration) {
        try {
            // Use Pi4J Manager to pulse the state of the pin
            return getPin(address).getState().pulse(duration);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return false;
        }
    }

    /**
     * Get a GPIO pin.
     *
     * @param address The address of the GPIO pin.
     * @return GpioPin instance
     */
    private GpioPin getPin(int address) {
        // Use Pi4J Manager to get the pin
        return pi4j.getGpioPin(address);
    }

    /**
     * Provision a GPIO pin with specified mode and name.
     *
     * @param address The address of the GPIO pin.
     * @param mode GpioPinPhysicalMode enum value for pin mode.
     * @param name The name of the GPIO pin.
     * @return True if successful.
     */
    private boolean provisionGpioPin(int address, String mode, String name) {
        try {
            // Use Pi4J Manager to provision the pin
            return pi4j.provisionGpioPin(address, mode, name);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return false;
        }
    }
}
