package be.webtechie.javaspringrestgpio.controller;

import be.webtechie.javaspringrestgpio.manager.GpioManager;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides a REST interface with the pins.
 */
@RestController
@RequestMapping("gpio")
public class GpioRestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GpioManager gpioManager;

    public GpioRestController(GpioManager gpioManager) {
        this.gpioManager = gpioManager;
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
        return this.gpioManager.provisionDigitalInputPin(address, name);
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
        return this.gpioManager.provisionDigitalOutputPin(address, name);
    }

    /**
     * Get the current state of the pins.
     *
     * @return {@link Collection} of {@link GpioPin}.
     */
    @GetMapping(path = "provision/list", produces = "application/json")
    public  Map<String, Map<String, String>> getProvisionList() {
        final Map<Integer, Object> list = this.gpioManager.getProvisionedPins();

        final Map<String, Map<String, String>> map = new TreeMap<>();

        for (final Entry<Integer, Object> entry : list.entrySet()) {
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
     * Get the current state of the pins.
     *
     * @return The value read from the pin
     */
    @GetMapping(path = "state/{address}", produces = "application/json")
    public String getState(@PathVariable("address") long address) {
        try {
            return String.valueOf(this.gpioManager.getState((int) address).getValue());
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
            return String.valueOf(this.gpioManager
                    .setPinDigitalState((int) address, (int) value));
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
        return this.gpioManager.togglePin((int) address);
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
        return this.gpioManager.pulsePin((int) address, duration);
    }
}
