package be.webtechie.i2c;

import be.webtechie.i2c.definition.Board;
import be.webtechie.i2c.definition.Relay;
import be.webtechie.i2c.definition.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Controller to set the relay states on different boards.
 */
public class RelayController {

    /**
     * Set the state of the all the relays on the given boards.
     *
     * @param boards List of Enum Board
     * @param relays List of Enum Relay
     * @param state Enum State
     */
    public static void setRelays(List<Board> boards, List<Relay> relays, State state) {
        for (Board board : boards) {
            for (Relay relay : relays) {
                setRelay(board, relay, state);
            }
        }
    }

    /**
     * Set the state of the relay on the given board.
     *
     * @param board Enum Board
     * @param relay Enum Relay
     * @param state Enum State
     */
    public static void setRelay(Board board, Relay relay, State state) {
        String cmd = "i2cset -y 1"
                + " " + String.format("0x%02X", board.getAddress())
                + " " + String.format("0x%02X", relay.getChannel())
                + " " + String.format("0x%02X", state.getValue());

        execute(cmd);

        System.out.println(relay + " on " + board + " set to " + state
                + " with command: " + cmd);
    }

    /**
     * Execute the given command, this is called by the public methods.
     *
     * @param cmd String command to be executed.
     */
    private static void execute(String cmd) {
        try {
            // Get a process to be able to do native calls on the operating system.
            // You can compare this to opening a terminal window and running a command.
            Process p = Runtime.getRuntime().exec(cmd);

            // We don't need the process anymore.
            p.destroy();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /*
    // I²C seems to be broken in Pi4J when used with after Java 8.
    // So below code doesn't work, but is added here as a reference.

    private I2CBus i2c;

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
    */
}
