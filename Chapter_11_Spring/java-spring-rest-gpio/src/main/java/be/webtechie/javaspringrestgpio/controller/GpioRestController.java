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
     * @param bcm The bcm of the GPIO pin.
     * @param id    The id of the GPIO pin.
     * @return True if successful.
     */
    @PostMapping(path = "input/{bcm}/{id}", produces = "application/json")
    public DigitalInput provisionDigitalInputPin(@PathVariable("bcm") int bcm, @PathVariable("id") String id) {
        // Use Pi4J Manager to provision digital input pin
        return pi4JManager.provisionDigitalInputPin(bcm, id);
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param bcm The bcm of the GPIO pin.
     * @param id    The id of the GPIO pin.
     * @return True if successful.
     */
    @PostMapping(path = "output/{bcm}/{id}", produces = "application/json")
    public DigitalOutput provisionDigitalOutputPin(@PathVariable("bcm") int bcm, @PathVariable("id") String id) {
        // Use Pi4J Manager to provision digital output pin
        return pi4JManager.provisionDigitalOutputPin(bcm, id);
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
     * @param bcm The bcm of the GPIO pin.
     * @return The value read from the pin
     */
    @GetMapping(path = "state/{bcm}", produces = "application/json")
    public String getState(@PathVariable("bcm") long bcm) {
        try {
            return String.valueOf(pi4JManager.getState((int) bcm));
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return ex.getMessage();
        }
    }

    /**
     * Set the state of a pin.
     *
     * @param bcm The bcm of the GPIO pin.
     * @param value   Possible values: 1 as HIGH, all else LOW.
     * @return True if successful.
     */
    @PostMapping(path = "digital/state/{bcm}/{value}", produces = "application/json")
    public String setPinDigitalState(@PathVariable("bcm") long bcm, @PathVariable("value") long value) {
        try {
            return String.valueOf(pi4JManager.setPinDigitalState((int) bcm, value == 1 ? DigitalState.HIGH : DigitalState.LOW));
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return ex.getMessage();
        }
    }

    /**
     * Toggle a pin.
     *
     * @param bcm The bcm of the GPIO pin.
     * @return True if successful.
     */
    @PostMapping(path = "digital/toggle/{bcm}", produces = "application/json")
    public String togglePin(@PathVariable("bcm") long bcm) {
        return pi4JManager.togglePin((int) bcm);
    }

}