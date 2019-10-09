package be.webtechie.javafxspringledcontroller.event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<EventListener> eventListeners = new ArrayList<>();

    public void addListener(EventListener eventListener) {
        this.eventListeners.add(eventListener);
    }

    public void sendEvent(String event) {
        this.eventListeners.forEach(l -> l.onQueueMessage(event));
    }
}
