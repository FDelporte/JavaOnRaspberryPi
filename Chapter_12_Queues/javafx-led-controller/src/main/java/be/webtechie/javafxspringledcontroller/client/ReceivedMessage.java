package be.webtechie.javafxspringledcontroller.client;

import be.webtechie.javafxspringledcontroller.led.LedCommand;

public class ReceivedMessage {

    private final String timestamp;
    private final LedCommand ledCommand;

    public ReceivedMessage(String timestamp, LedCommand ledCommand) {
        this.timestamp = timestamp;
        this.ledCommand = ledCommand;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LedCommand getLedCommand() {
        return ledCommand;
    }
}
