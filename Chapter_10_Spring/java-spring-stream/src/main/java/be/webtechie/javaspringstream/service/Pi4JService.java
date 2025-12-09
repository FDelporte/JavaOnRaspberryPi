package be.webtechie.javaspringstream.service;

import be.webtechie.javaspringstream.dto.ButtonState;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@Service
public class Pi4JService {

    private static final Logger logger = LoggerFactory.getLogger(Pi4JService.class);

    private static final int BCM_BUTTON = 22; // Physical pin 15

    private final DigitalInput button;

    public Pi4JService() {
        // Initialize Pi4J
        var pi4j = Pi4J.newAutoContext();

        // Initialize the pin
        this.button = pi4j.digitalInput().create(BCM_BUTTON, "Button on BCM " + BCM_BUTTON);
    }

    public Flux<ButtonState> getButtonStates() {
        return Flux.fromStream(Stream.generate(this::getButtonState))
                .delayElements(Duration.ofSeconds(1));
    }

    private ButtonState getButtonState() {
       return new ButtonState(button);
    }
}
