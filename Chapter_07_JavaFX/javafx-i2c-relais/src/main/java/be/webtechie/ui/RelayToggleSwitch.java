package be.webtechie.ui;

import be.webtechie.i2c.RelayController;
import be.webtechie.i2c.definition.Board;
import be.webtechie.i2c.definition.Relay;
import be.webtechie.i2c.definition.State;
import org.controlsfx.control.ToggleSwitch;

/**
 * Extends a ToggleSwitch so it can be constructed with the board and relay for cleaner code.
 */
public class RelayToggleSwitch extends ToggleSwitch {

    public RelayToggleSwitch(String text, Board board, Relay relay) {
        this.setText(text);
        this.selectedProperty().addListener((observable, oldValue, selected) ->
                RelayController.setRelay(board, relay,
                        selected ? State.STATE_ON : State.STATE_OFF));
    }
}
