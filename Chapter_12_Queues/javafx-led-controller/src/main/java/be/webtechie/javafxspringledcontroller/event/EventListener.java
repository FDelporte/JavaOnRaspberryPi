package be.webtechie.javafxspringledcontroller.event;

import be.webtechie.javafxspringledcontroller.led.LedCommand;

public interface EventListener {

    /**
     * Whenever a new {@link LedCommand} is received from Mosquitto, all listeners will be notified so they can handle
     * it for their own use.
     *
     * @param ledCommand {@link LedCommand}
     */
    void onQueueMessage(LedCommand ledCommand);
}
