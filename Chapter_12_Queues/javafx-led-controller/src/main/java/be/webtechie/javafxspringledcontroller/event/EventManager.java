package be.webtechie.javafxspringledcontroller.event;

import be.webtechie.javafxspringledcontroller.led.LedCommand;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<EventListener> eventListeners = new ArrayList<>();

    public void addListener(EventListener eventListener) {
        this.eventListeners.add(eventListener);
    }

    public void sendEvent(LedCommand ledCommand) {
        this.eventListeners.forEach(l -> l.onQueueMessage(ledCommand));
    }
}
