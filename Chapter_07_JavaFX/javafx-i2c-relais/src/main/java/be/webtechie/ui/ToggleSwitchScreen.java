package be.webtechie.ui;

import be.webtechie.i2c.definition.Board;
import be.webtechie.i2c.definition.Relay;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Builder for a screen with two rows with four toggle switches.
 */
public class ToggleSwitchScreen extends VBox {

    public ToggleSwitchScreen() {
        this.setSpacing(25);
        this.setPadding(new Insets(25));

        HBox row1 = new HBox();
        row1.setSpacing(25);
        this.getChildren().addAll(row1);

        row1.getChildren().add(new RelayToggleSwitch("Relais 1",
                Board.BOARD_1, Relay.RELAY_1));
        row1.getChildren().add(new RelayToggleSwitch("Relais 2",
                Board.BOARD_1, Relay.RELAY_2));
        row1.getChildren().add(new RelayToggleSwitch("Relais 3",
                Board.BOARD_1, Relay.RELAY_3));
        row1.getChildren().add(new RelayToggleSwitch("Relais 4",
                Board.BOARD_1, Relay.RELAY_4));

        HBox row2 = new HBox();
        row2.setSpacing(25);
        this.getChildren().addAll(row2);

        row2.getChildren().add(new RelayToggleSwitch("Relais 5",
                Board.BOARD_2, Relay.RELAY_1));
        row2.getChildren().add(new RelayToggleSwitch("Relais 6",
                Board.BOARD_2, Relay.RELAY_2));
        row2.getChildren().add(new RelayToggleSwitch("Relais 7",
                Board.BOARD_2, Relay.RELAY_3));
        row2.getChildren().add(new RelayToggleSwitch("Relais 8",
                Board.BOARD_2, Relay.RELAY_4));

        System.out.println("Toggle switch screen created");
    }
}
