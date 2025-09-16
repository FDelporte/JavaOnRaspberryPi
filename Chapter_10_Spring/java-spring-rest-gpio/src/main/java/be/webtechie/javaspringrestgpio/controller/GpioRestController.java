package be.webtechie.javaspringrestgpio.controller;

import be.webtechie.javaspringrestgpio.manager.Pi4JManager;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gpio")
public class GpioRestController {

    private static final Logger logger = LoggerFactory.getLogger(GpioRestController.class);
    private final Pi4JManager pi4JManager;

    public GpioRestController(Pi4JManager pi4JManager) {
        this.pi4JManager = pi4JManager;
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name    The name of the GPIO pin.
     * @return True if successful.
     */
    @PostMapping(path = "input/{address}/{name}", produces = "application/json")
    public DigitalInput provisionDigitalInputPin(@PathVariable("address") int address, @PathVariable("name") String name) {
        // Use Pi4J Manager to provision digital input pin
        return pi4JManager.provisionDigitalInputPin(address, name);
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name    The name of the GPIO pin.
     * @return True if successful.
     */
    @PostMapping(path = "output/{address}/{name}", produces = "application/json")
    public DigitalOutput provisionDigitalOutputPin(@PathVariable("address") int address, @PathVariable("name") String name) {
        // Use Pi4J Manager to provision digital output pin
        return pi4JManager.provisionDigitalOutputPin(address, name);
    }

    /**
     * Get the current state of the pins.
     *
     * @return {@link List} of {@link DigitalInput}.
     */
    @GetMapping(path = "input", produces = "application/json")
    public List<DigitalInput> getInputs() {
        return pi4JManager.getInputs();
    }

    /**
     * Get the current state of the pins.
     *
     * @return {@link List} of {@link DigitalOutput}.
     */
    @GetMapping(path = "output", produces = "application/json")
    public List<DigitalOutput> getOutputs() {
        return pi4JManager.getOutputs();
    }

    /**
     * Get the current state of a pin.
     *
     * @param address The address of the GPIO pin.
     * @return The value read from the pin
     */
    @GetMapping(path = "state/{address}", produces = "application/json")
    public String getState(@PathVariable("address") long address) {
        try {
            return String.valueOf(pi4JManager.getState((int) address));
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return ex.getMessage();
        }
    }

    /**
     * Set the state of a pin.
     *
     * @param address The address of the GPIO pin.
     * @param value   Possible values: 1 as HIGH, all else LOW.
     * @return True if successful.
     */
    @PostMapping(path = "digital/state/{address}/{value}", produces = "application/json")
    public String setPinDigitalState(@PathVariable("address") long address, @PathVariable("value") long value) {
        try {
            return String.valueOf(pi4JManager.setPinDigitalState((int) address, value == 1 ? DigitalState.HIGH : DigitalState.LOW));
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
    @PostMapping(path = "digital/toggle/{address}", produces = "application/json")
    public String togglePin(@PathVariable("address") long address) {
        return pi4JManager.togglePin((int) address);
    }

}