package be.webtechie.javaspringstream.service;

import be.webtechie.javaspringstream.dto.DistanceMeasurement;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DistanceService {

    private static final Logger logger = LoggerFactory.getLogger(DistanceService.class);

    private static final Pin PIN_TRIGGER = RaspiPin.GPIO_01;    // BCM 18
    private static final Pin PIN_ECHO = RaspiPin.GPIO_05;       // BCM 24

    private final GpioPinDigitalOutput trigger;
    private final GpioPinDigitalInput echo;

    public DistanceService() {
        // Initialize the GPIO controller
        GpioController gpio = GpioFactory.getInstance();

        // Initialize the pins
        this.trigger = gpio.provisionDigitalOutputPin(PIN_TRIGGER, "Trigger", PinState.LOW);
        this.echo = gpio.provisionDigitalInputPin(PIN_ECHO, "Echo", PinPullResistance.PULL_UP);
    }

    public Flux<DistanceMeasurement> getDistances() {
        return Flux.fromStream(Stream.generate(() -> this.getDistanceMeasurement()))
                .delayElements(Duration.ofSeconds(1));
    }

    private DistanceMeasurement getDistanceMeasurement() {
        try {
            // Set trigger high for 0.01ms
            this.trigger.pulse(10, PinState.HIGH, true, TimeUnit.NANOSECONDS);

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
