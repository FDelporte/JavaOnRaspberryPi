package be.webtechie.javaspringrestgpio.manager;

import com.pi4j.Pi4J;
import com.pi4j.boardinfo.model.BoardInfo;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.platform.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service to interact with the GPIO pins, using the Pi4J library.
 */
@Service
public class Pi4JManager {
    private final Map<Integer, DigitalInput> inputs;
    private final Map<Integer, DigitalOutput> outputs;

    /**
     * The GPIO controller.
     */
    private final Context pi4j;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructor which initializes the Pi4J {@link Context}.
     */
    public Pi4JManager() {
        this.pi4j = Pi4J.newAutoContext();
        this.inputs = new HashMap<>();
        this.outputs = new HashMap<>();
    }

    /**
     * Provision a GPIO as digital input pin.
     *
     * @param bcm The BCM number of the GPIO pin.
     * @param id  The ID of the GPIO pin.
     * @return True if successful.
     */
    public DigitalInput provisionDigitalInputPin(int bcm, String id) {
        checkBcm(bcm);

        var input = pi4j.digitalInput().create(bcm, id);
        inputs.put(bcm, input);
        return input;
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param bcm The BCM number of the GPIO pin.
     * @param id  The ID of the GPIO pin.
     * @return True if successful.
     */
    public DigitalOutput provisionDigitalOutputPin(int bcm, String id) {
        checkBcm(bcm);

        var output = pi4j.digitalOutput().create(bcm, id);
        outputs.put(bcm, output);
        return output;
    }

    private void checkBcm(int bcm) {
        if (inputs.containsKey(bcm) || outputs.containsKey(bcm)) {
            throw new IllegalArgumentException("Pin " + bcm + " is already provisioned.");
        }
    }

    /**
     * Get the current state of an input pin.
     *
     * @param bcm The BCM number of the GPIO pin.
     * @return The value read from the pin
     */
    public String getState(int bcm) {
        if (!inputs.containsKey(bcm)) {
            return "Not available";
        }
        try {
            return String.valueOf(outputs.get(bcm).state().getValue());
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return ex.getMessage();
        }
    }

    /**
     * Set the state of an output pin.
     *
     * @param bcm   The BCM number of the GPIO pin.
     * @param state {@link DigitalState}
     * @return True if successful.
     */
    public String setPinDigitalState(int bcm, DigitalState state) {
        if (!outputs.containsKey(bcm)) {
            return "Not available";
        }
        try {
            outputs.get(bcm).state(state);
            return "Done";
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return "Error: " + ex.getMessage();
        }
    }

    /**
     * Toggle an output pin.
     *
     * @param bcm The BCM number of the GPIO pin.
     * @return True if successful.
     */
    public String togglePin(int bcm) {
        if (!outputs.containsKey(bcm)) {
            return "Not available";
        }
        try {
            outputs.get(bcm).toggle();
            return "Done";
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());

            return "Error: " + ex.getMessage();
        }
    }

    public BoardInfo getBoardInfo() {
        return pi4j.boardInfo();
    }

    public Platform getPlatform() {
        return pi4j.platform();
    }

    public List<DigitalInput> getInputs() {
        return List.copyOf(inputs.values());
    }

    public List<DigitalOutput> getOutputs() {
        return List.copyOf(outputs.values());
    }
}
