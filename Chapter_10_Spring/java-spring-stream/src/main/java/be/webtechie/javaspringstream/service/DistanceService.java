package be.webtechie.javaspringstream.service;

import be.webtechie.javaspringstream.dto.DistanceMeasurement;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
public class DistanceService {

    private static final Logger logger = LoggerFactory.getLogger(DistanceService.class);

    private static final int PIN_TRIGGER = 18;    // BCM 18
    private static final int PIN_ECHO = 24;       // BCM 24

    private final DigitalOutput trigger;
    private final DigitalInput echo;

    public DistanceService() {
        // Initialize Pi4J
        var pi4j = Pi4J.newAutoContext();

        // Initialize the pins
        this.trigger = pi4j.digitalOutput().create(PIN_TRIGGER, "Trigger");
        this.trigger.low();
        this.echo = pi4j.digitalInput().create(PIN_ECHO, "Echo");
    }

    public Flux<DistanceMeasurement> getDistances() {
        return Flux.fromStream(Stream.generate(this::getDistanceMeasurement))
                .delayElements(Duration.ofSeconds(1));
    }

    private DistanceMeasurement getDistanceMeasurement() {
        try {
            // Set trigger high for 0.01ms
            this.trigger.pulse(10, TimeUnit.MILLISECONDS, DigitalState.HIGH);

            // Start the measurement
            while (this.echo.isLow()) {
                // Wait until the echo pin is high,
                // indicating the ultrasound was sent
            }
            long start = System.nanoTime();

            // Wait till measurement is finished
            while (this.echo.isHigh()) {
                // Wait until the echo pin is low,
                // indicating the ultrasound was received back
            }
            long end = System.nanoTime();

            // Output the distance
            float measuredSeconds = (end - start) / 1000000000F;
            int distance = Math.round(measuredSeconds * 34300 / 2);

            logger.info("Measured distance is: {}  for {}s", distance, measuredSeconds);

            return new DistanceMeasurement(distance, measuredSeconds);
        } catch (Exception ex) {
            logger.error("Error: {}", ex.getMessage());
        }

        return null;
    }
}
