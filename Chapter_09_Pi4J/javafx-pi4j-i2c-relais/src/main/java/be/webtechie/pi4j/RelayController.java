package be.webtechie.pi4j;

import be.webtechie.pi4j.definition.Board;
import be.webtechie.pi4j.definition.Relay;
import be.webtechie.pi4j.definition.State;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import java.io.IOException;

/**
 * I²C controller to set the relay states on different boards
 */
public class RelayController {

    private I2CBus i2c;

    /**
     * Constructor which initializes the I2CFactory.
     */
    public RelayController() {
        try {
            this.i2c = I2CFactory.getInstance(I2CBus.BUS_1);
        } catch (UnsupportedBusNumberException ex) {
            System.err.println("Bus number not supported: "
                    + ex.getMessage());
            this.i2c = null;
        } catch (IOException ex) {
            System.err.println("Error while initializing the relay controller: "
                    + ex.getMessage());
            this.i2c = null;
        }
    }

    /**
     * Set the state of the relay on the given board.
     *
     * @param board Enum Board
     * @param relay Enum Relay
     * @param state Enum State
     */
    public void setRelay(Board board, Relay relay, State state) {
        System.out.println("Setting relay on board "
                + String.format("0x%02X", board.getAddress())
                + ", relay " + String.format("0x%02X", relay.getChannel())
                + ", state " + String.format("0x%02X", state.getValue()));

        if (this.i2c == null) {
            System.err.println("I²C not available");
            return;
        }

        try {
            I2CDevice device = i2c.getDevice(board.getAddress());
            device.write(relay.getChannel(), state.getValue());
        } catch (IOException ex) {
            System.err.println("Error while setting relay: " + ex.getMessage());
        }
    }
}
