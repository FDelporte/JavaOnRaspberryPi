package be.webtechie.javaspringstream.dto;

import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * Object containing a distance measurement.
 */
public record ButtonState(
        long timestamp,
        String id,
        String name,
        DigitalState state) {
    /**
     * Constructor which will add the current timestamp.
     */
    public ButtonState(DigitalInput input) {
        this(System.currentTimeMillis(), input.id(), input.name(), input.state());
    }
}