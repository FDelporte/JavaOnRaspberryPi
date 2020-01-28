package be.webtechie.ui;

import be.webtechie.i2c.RelayController;
import be.webtechie.i2c.definition.Board;
import be.webtechie.i2c.definition.Relay;
import be.webtechie.i2c.definition.State;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.ToggleSwitch;

/**
 * Builder for a screen with two rows with four toggle switches.
 */
public class ToggleSwitchScreen extends VBox {

    public ToggleSwitchScreen() {
        this.setSpacing(25);
        this.setPadding(new Insets(25));

        this.getChildren().add(this.createRow(Board.BOARD_1, 0));
        this.getChildren().add(this.createRow(Board.BOARD_2, 4));

        System.out.println("Toggle switch screen created");
    }

    /**
     * Create a row with four toggle switches for the given board.
     *
     * @param board The board to be controlled
     * @param offset Offset for the number showed in the label
     * @return The created HBox
     */
    private HBox createRow(Board board, int offset) {
        HBox row = new HBox();
        row.setSpacing(25);

        row.getChildren().add(this.createRelayToggleSwitch(
                "Relais " + (offset + 1),
                board, Relay.RELAY_1));
        row.getChildren().add(this.createRelayToggleSwitch(
                "Relais " + (offset + 2),
                board, Relay.RELAY_2));
        row.getChildren().add(this.createRelayToggleSwitch(
                "Relais " + (offset + 3),
                board, Relay.RELAY_3));
        row.getChildren().add(this.createRelayToggleSwitch(
                "Relais " + (offset + 4),
                board, Relay.RELAY_4));

        return row;
    }

    /**
     * Create a ToggleSwitch which will call the RelayController on change.
     *
     * @param label Label for the toggle switch
     * @param board The board to be controlled
     * @param relay The relay on the board to be controlled
     * @return The created ToggleSwitch
     */
    private ToggleSwitch createRelayToggleSwitch(String label, Board board, Relay relay) {
        ToggleSwitch toggleSwitch = new ToggleSwitch();
        toggleSwitch.setText(label);
        toggleSwitch.selectedProperty().addListener((observable, oldValue, selected) ->
                RelayController.setRelay(board, relay,
                        selected ? State.STATE_ON : State.STATE_OFF));
        return toggleSwitch;
    }
}
