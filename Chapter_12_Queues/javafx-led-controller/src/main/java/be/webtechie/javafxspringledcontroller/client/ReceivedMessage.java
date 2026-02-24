package be.webtechie.javafxspringledcontroller.client;

import be.webtechie.javafxspringledcontroller.led.LedCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper class to add a {@link LedCommand} to a table with a timestamp.
 */
public class ReceivedMessage {

    private final String timestamp;
    private final LedCommand ledCommand;

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ReceivedMessage(LedCommand ledCommand) {
        this.timestamp = LocalDateTime.now().format(dateFormat);
        this.ledCommand = ledCommand;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LedCommand getLedCommand() {
        return ledCommand;
    }
}
