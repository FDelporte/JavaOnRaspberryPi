package be.webtechie.javaspringrestgpio.controller;

import be.webtechie.javaspringrestgpio.manager.Pi4JManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

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
     * @param name The name of the GPIO pin.
     * @return True if successful.
     */
    @PostMapping(path = "provision/digital/input/{address}/{name}", produces = "application/json")
    public boolean provisionDigitalInputPin(@PathVariable("address")  int address, @PathVariable("name")  String name) {
        // Use Pi4J Manager to provision digital input pin
        return pi4JManager.provisionDigitalInputPin(address, name);
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name The name of the GPIO pin.
     * @return True if successful.
     */
    @PostMapping(path = "provision/digital/output/{address}/{name}", produces = "application/json")
    public boolean provisionDigitalOutputPin(@PathVariable("address")  int address, @PathVariable("name")  String name) {
        // Use Pi4J Manager to provision digital output pin
        return pi4JManager.provisionDigitalOutputPin(address, name);
    }

    /**
     * Get the current state of the pins.
     *
     * @return {@link Collection} of {@link GpioPin}.
     */
    @GetMapping(path = "provision/list", produces = "application/json")
    public  Map<String, Map<String, String>> getProvisionList() {
        final Map<Integer, Object> list = pi4JManager.getProvisionedPins();

        final Map<String, Map<String, String>> map = new TreeMap<>();

        for (final Map.Entry<Integer, Object> entry : list.entrySet()) {
            final Map<String, String> innerMap = new TreeMap<>();

            innerMap.put("address", String.valueOf(entry.getKey()));
            innerMap.put("type", entry.getValue().getClass().getName());

            if (entry.getValue() instanceof GpioPinDigitalOutput) {
                final GpioPinDigitalOutput digitalPin = (GpioPinDigitalOutput) entry.getValue();

                innerMap.put("name", digitalPin.getName());
                innerMap.put("pinName", digitalPin.getPin().getName());
                innerMap.put("mode", digitalPin.getMode().getName());
                innerMap.put("state", String.valueOf(digitalPin.getState().getValue()));
            }

            map.put("ProvisionedPin_" + entry.getKey(), innerMap);
        }

        return map;
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
            return String.valueOf(pi4JManager.getState((int) address).getValue());
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
    @PostMapping(path = "digital/state/{address}/{value}", produces = "application/json")
    public String setPinDigitalState(@PathVariable("address") long address, @PathVariable("value") long value) {
        try {
            return String.valueOf(pi4JManager.setPinDigitalState((int) address, (int) value));
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
    public boolean togglePin(@PathVariable("address") long address) {
        return pi4JManager.togglePin((int) address);
    }

    /**
     * Pulse a pin.
     *
     * @param address The address of the GPIO pin.
     * @param duration The duration in milliseconds.
     * @return True if successful.
     */
    @PostMapping(path = "digital/pulse/{address}/{duration}", produces = "application/json")
    public boolean pulsePin(@PathVariable("address") long address, @PathVariable("duration") long duration) {
        return pi4JManager.pulsePin((int) address, duration);
    }
}