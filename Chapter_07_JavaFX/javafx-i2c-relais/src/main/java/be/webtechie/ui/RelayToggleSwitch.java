package be.webtechie.ui;

import be.webtechie.pi4j.RelayController;
import be.webtechie.pi4j.definition.Board;
import be.webtechie.pi4j.definition.Relay;
import be.webtechie.pi4j.definition.State;
import org.controlsfx.control.ToggleSwitch;

/**
 * Extends a ToggleSwitch so it can be constructed with the board and relay
 * for cleaner code.
 */
public class RelayToggleSwitch extends ToggleSwitch {
    public RelayToggleSwitch(String text, Board board, Relay relay) {
        this.setText(text);
        this.selectedProperty().addListener((observable, oldValue, selected) ->
                RelayController.setRelay(board, relay,
                        selected ? State.RELAY_ON : State.RELAY_OFF));
    }
}
