package be.webtechie.javafxspringledcontroller.event;

import be.webtechie.javafxspringledcontroller.led.LedCommand;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    /**
     * The list with components to be notified of a new LedCommand received from Mosquitto.
     */
    private List<EventListener> eventListeners = new ArrayList<>();

    /**
     * Used by every component which wants to be notified of new events.
     *
     * @param eventListener {@link EventListener}
     */
    public void addListener(EventListener eventListener) {
        this.eventListeners.add(eventListener);
    }

    /**
     * Used by Mosquitto callback to forward a received messaged to all components in the application who were added
     * as a listener.
     *
     * @param ledCommand {@link LedCommand}
     */
    public void sendEvent(LedCommand ledCommand) {
        this.eventListeners.forEach(l -> l.onQueueMessage(ledCommand));
    }
}
